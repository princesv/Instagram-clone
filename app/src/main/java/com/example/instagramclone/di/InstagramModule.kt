package com.example.instagramclone.di

import com.example.instagramclone.data.AuthenticationRepositoryImpl
import com.example.instagramclone.domain.repository.AuthenticationRepository
import com.example.instagramclone.domain.use_case.AuthenticationUseCase
import com.example.instagramclone.domain.use_case.FirebaseAuthState
import com.example.instagramclone.domain.use_case.FirebaseSignIn
import com.example.instagramclone.domain.use_case.FirebaseSignOut
import com.example.instagramclone.domain.use_case.FirebaseSignUp
import com.example.instagramclone.domain.use_case.IsUserAuthenticated
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InstagramModule {
    @Singleton
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun providesFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun providesFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun providesAuthenticationRepository(auth: FirebaseAuth, firestore: FirebaseFirestore):AuthenticationRepository{
        return AuthenticationRepositoryImpl(auth,firestore)
    }

    @Singleton
    @Provides
    fun providesAuthenticationUseCase(repository: AuthenticationRepository)=AuthenticationUseCase(
        isUserAuthenticated = IsUserAuthenticated(repository),
        firebaseAuthState = FirebaseAuthState(repository),
        firebaseSignIn = FirebaseSignIn(repository),
        firebaseSignUp = FirebaseSignUp(repository),
        firebaseSignOut = FirebaseSignOut(repository)
    )
}