package com.example.instagramclone.presentation
import android.icu.number.Scale
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.instagramclone.R
import com.example.instagramclone.presentation.authentication.AuthenticationViewModel
import com.example.instagramclone.utils.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (navController: NavController, authViewModel: AuthenticationViewModel){
    val authValue=authViewModel.isUserAuthenticated
    val scale= remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = false) {
        scale.animateTo(targetValue = 0.5f,
            animationSpec = tween(durationMillis = 1500, easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            })
            )
        delay(1500)
        if(authValue){
            navController.navigate(Screens.FeedsScreen.route){
                popUpTo(Screens.LoginScreen.route){
                    inclusive=true
                }
            }
        }else{
            navController.navigate(Screens.LoginScreen.route){
                popUpTo(Screens.LoginScreen.route){
                    inclusive=true
                }
            }
        }
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.instagram),
            modifier = Modifier.scale(scale.value),
            contentDescription = "Splash Screen")
    }
}