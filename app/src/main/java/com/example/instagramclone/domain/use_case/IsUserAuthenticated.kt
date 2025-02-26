package com.example.instagramclone.domain.use_case

import com.example.instagramclone.domain.repository.AuthenticationRepository
import javax.inject.Inject

class IsUserAuthenticated @Inject constructor(private val repository: AuthenticationRepository) {

    operator fun invoke()=repository.isUserAuthenticatedInFirebase()
}