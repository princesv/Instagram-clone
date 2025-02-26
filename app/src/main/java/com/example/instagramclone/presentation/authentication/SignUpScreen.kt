package com.example.instagramclone.presentation.authentication

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.instagramclone.R
import com.example.instagramclone.presentation.Toast
import com.example.instagramclone.utils.Response
import com.example.instagramclone.utils.Screens

@Composable
fun SignUpScreen(navController: NavHostController, viewModel: AuthenticationViewModel){
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally) {

            val emailState= remember {
                mutableStateOf("")
            }
            val passwordState= remember {
                mutableStateOf("")
            }
            val usernameState= remember {
                mutableStateOf("")
            }
            Image(painter = painterResource(R.drawable.instagram),
                contentDescription = "Login Screen Image",
                modifier = Modifier
                    .width(250.dp)
                    .padding(8.dp)
                    .padding(top = 16.dp))
            Text(text = "Sign Up",modifier = Modifier.padding(10.dp), fontSize = 30.sp, fontFamily = FontFamily.SansSerif)
            OutlinedTextField(value = usernameState.value, onValueChange = {
                usernameState.value=it
            },modifier = Modifier.padding(10.dp)
                , label = { Text(text = "enter your username") })
            OutlinedTextField(value = emailState.value, onValueChange = {
                emailState.value=it
            },modifier = Modifier.padding(10.dp)
                , label = { Text(text = "enter your email") })
            OutlinedTextField(value = passwordState.value, onValueChange = {
                passwordState.value=it
            },modifier = Modifier.padding(10.dp)
                , label = { Text(text = "enter your password") }
                , visualTransformation = PasswordVisualTransformation())
            Button(onClick = {
                viewModel.signUp(emailState.value,passwordState.value,usernameState.value)
            }) {
                Text(text = "Sign up")
                when(val response=viewModel.signUpState.value){
                    is Response.Error -> {
                        response.message?.let {
                            Toast(message = it)
                        }
                    }
                    Response.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                    }
                    is Response.Success -> {
                        if (response.data) {
                            navController.navigate(Screens.FeedsScreen.route) {
                                popUpTo(Screens.SignUpScreen.route) {
                                    inclusive = true
                                }
                            }
                        }else{
                            Toast(message = "Signup failed")
                        }
                    }
                }
            }
            Text(text = "Already have an account? Sign in",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navController.navigate(Screens.LoginScreen.route) {
                            launchSingleTop = true
                        }
                    }
            )

        }

    }
}

@Preview(backgroundColor = Color.WHITE.toLong(), showBackground = true)
@Composable
fun SignupForm(){

    }