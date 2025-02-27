package com.example.instagramclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instagramclone.presentation.main.FeedsScreen
import com.example.instagramclone.presentation.authentication.LoginScreen
import com.example.instagramclone.presentation.authentication.SignUpScreen
import com.example.instagramclone.presentation.SplashScreen
import com.example.instagramclone.presentation.authentication.AuthenticationViewModel
import com.example.instagramclone.presentation.main.ProfileScreen
import com.example.instagramclone.presentation.main.SearchScreen
import com.example.instagramclone.ui.theme.InstagramCloneTheme
import com.example.instagramclone.utils.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InstagramCloneTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController= rememberNavController()
                    val authViewModel:AuthenticationViewModel= hiltViewModel()
                    InstaGramCloneApp(navController,authViewModel)
                }
            }
        }
    }
}

@Composable
fun InstaGramCloneApp(navController: NavHostController,authViewModel: AuthenticationViewModel){
    NavHost(navController, startDestination = Screens.SplashScreen.route) {
        composable(Screens.SplashScreen.route) {
            SplashScreen(navController, authViewModel)
        }
        composable(Screens.LoginScreen.route) {
            LoginScreen(navController,authViewModel)
        }
        composable(Screens.SignUpScreen.route){
            SignUpScreen(navController,authViewModel)
        }
        composable(Screens.FeedsScreen.route){
            FeedsScreen(navController)
        }
        composable(Screens.ProfileScreen.route){
            ProfileScreen(navController)
        }
        composable(Screens.SearchScreen.route){
            SearchScreen(navController)
        }
    }
}