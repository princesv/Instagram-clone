package com.example.instagramclone.domain.repository

import com.example.instagramclone.utils.Response
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun isUserAuthenticatedInFirebase(): Boolean
    fun getFirebaseAuthState(): Flow<Boolean>
    fun firebaseSignInWithEmailAndPassword(email: String, password: String): Flow<Response<Boolean>>
    //fun sendEmailVerification(): Flow<Response<Boolean>>
    fun firebaseSignUpWithEmailAndPassword(email: String, password: String, username: String): Flow<Response<Boolean>>
    fun firebaseSignOut(): Flow<Response<Boolean>>
}