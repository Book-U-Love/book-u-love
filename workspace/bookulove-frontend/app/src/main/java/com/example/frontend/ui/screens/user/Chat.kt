package com.example.frontend.ui.screens.user

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.frontend.ui.components.ChatInfo


@Composable

fun Chat(navController: NavController){
    Box(){
        Column(){
            ChatInfo(navController)
            ChatInfo(navController)
            ChatInfo(navController)
        }

    }
}