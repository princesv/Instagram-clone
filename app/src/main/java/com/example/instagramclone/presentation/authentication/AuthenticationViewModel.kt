package com.example.instagramclone.presentation.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramclone.domain.use_case.AuthenticationUseCase
import com.example.instagramclone.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authUseCase: AuthenticationUseCase
):ViewModel(){
    val isUserAuthenticated get() = authUseCase.isUserAuthenticated()
    private val _isSignInState= mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signInState:State<Response<Boolean>> = _isSignInState
    private val _isSignUpState= mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signUpState:State<Response<Boolean>> = _isSignUpState
    private val _isSignOutState= mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState:State<Response<Boolean>> = _isSignOutState
    private val _firebaseAuthState= mutableStateOf<Boolean>(false)
    val firebaseAuthState:State<Boolean> = _firebaseAuthState

    fun signIn(email:String,password:String){
        viewModelScope.launch {
            authUseCase.firebaseSignIn(email,password).collect{
                _isSignInState.value=it
            }
        }
    }
    fun signUp(email:String,password:String,username:String){
        viewModelScope.launch {
            authUseCase.firebaseSignUp(email,password,username).collect{
                _isSignUpState.value=it
            }
        }
    }
    fun signOut(){
        viewModelScope.launch {
            authUseCase.firebaseSignOut().collect {
                _isSignOutState.value = it
                if (it == Response.Success(true)) {
                    _isSignInState.value = Response.Success(false)
                }
            }
        }
    }
    fun getFirebaseAuthState(){
        viewModelScope.launch {
            authUseCase.firebaseAuthState().collect{
                _firebaseAuthState.value=it
            }
        }
    }
}