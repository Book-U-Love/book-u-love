package com.example.frontend.ui.screens.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider
import androidx.navigation.NavController
import com.example.frontend.ui.components.ChatInfo


@Composable
@ExperimentalMaterial3Api
fun Chat(navController: NavController){
    var clickState by remember{
        mutableStateOf(false)
    }
    Box(){
        Column(){
            ChatInfo(navController)
            ChatInfo(navController)
            ChatInfo(navController)
        }
        Button(onClick = {
            clickState = true
        }) {
            Text(text="click")
        }
        if(clickState){
            Box(modifier=Modifier.fillMaxSize().background(Color.Black.copy(alpha=0.5f))){
                AlertDialog(
                    onDismissRequest = { clickState = false },
                    confirmButton = { Text(text = "test") },
                    title = { Text(text = "asdf") },
                    text = { Text(text = "asdf") }
                )
            }

        }
    }
}