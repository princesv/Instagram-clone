package com.example.instagramclone.data

import android.util.Log
import com.example.instagramclone.domain.model.Users
import com.example.instagramclone.domain.repository.AuthenticationRepository
import com.example.instagramclone.utils.Constants
import com.example.instagramclone.utils.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(private val auth:FirebaseAuth,
    private val firestore: FirebaseFirestore):AuthenticationRepository {
    override fun isUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser!=null
    }

    override fun getFirebaseAuthState(): Flow<Boolean> = callbackFlow{
        val authStateListener=FirebaseAuth.AuthStateListener {
            trySend(auth.currentUser==null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose(){
            auth.removeAuthStateListener(authStateListener)
        }
    }

    override fun firebaseSignInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Response<Boolean>> = flow{
        var operationSuccess=false
        try {
            emit(Response.Loading)
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {

            }.await()
            if(auth.currentUser!=null){
                operationSuccess=true
            }
            emit(Response.Success(operationSuccess))
        }catch (e:Exception){
            emit(Response.Error(e.message?:"An Unexpected Error"))
        }
    }

   /* override fun sendEmailVerification(): Flow<Response<Boolean>> {
        TODO("Not yet implemented")
    }

    */

    override fun firebaseSignUpWithEmailAndPassword(
        email: String,
        password: String,
        username: String
    ): Flow<Response<Boolean>> = flow{
        var operationSuccess=false
        try {
            emit(Response.Loading)
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {

            }.await()
            if(auth.currentUser!=null){
                operationSuccess=true
            }
            if(operationSuccess){
                val uid=auth.currentUser?.uid
                val user=Users(
                    email=email,
                    username=username,
                    password=password,
                    bio = "",
                    imageUrl = "",
                    userid = uid?:""
                )
                firestore.collection(Constants.COLLECTION_NAME_USERS).document(uid?:"").set(user).addOnCompleteListener {
                    operationSuccess=true
                }.await()
                emit(Response.Success(operationSuccess))
            }else{
                emit(Response.Success(operationSuccess))
            }
        }catch (e:Exception){
            emit(Response.Error(e.message?:"An Unexpected Error"))
        }
    }

    override fun firebaseSignOut(): Flow<Response<Boolean>> = flow{
        try {
            emit(Response.Loading)
            auth.signOut()
            emit(Response.Success(true))
        }catch (e:Exception){
            emit(Response.Error(e.message?:"An Unexpected Error"))
        }
    }
}