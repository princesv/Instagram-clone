package com.example.instagramclone.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.instagramclone.presentation.BottomNavigationItem
import com.example.instagramclone.presentation.BottomNavigationMenu

@Composable
fun ProfileScreen(navController: NavHostController){
    Column(modifier = Modifier.fillMaxWidth().systemBarsPadding()) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Profile screen")
        }
        BottomNavigationMenu(selectedItem = BottomNavigationItem.Profile, navController = navController)
    }
}