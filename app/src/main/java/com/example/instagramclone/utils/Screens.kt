package com.example.instagramclone.utils

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")
    object LoginScreen : Screens("login_screen")
    object SignUpScreen : Screens("signup_screen")
    object FeedsScreen : Screens("feeds_screen")
    object SearchScreen : Screens("search_screen")
    object ProfileScreen : Screens("profile_screen")
}