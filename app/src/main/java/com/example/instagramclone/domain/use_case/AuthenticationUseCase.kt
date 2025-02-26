package com.example.instagramclone.domain.use_case

data class AuthenticationUseCase(
    val isUserAuthenticated: IsUserAuthenticated,
    val firebaseSignIn: FirebaseSignIn,
    val firebaseSignOut: FirebaseSignOut,
    val firebaseSignUp: FirebaseSignUp,
    val firebaseAuthState: FirebaseAuthState
)