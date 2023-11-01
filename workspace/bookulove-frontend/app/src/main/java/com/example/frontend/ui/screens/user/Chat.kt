package com.example.frontend.ui.screens.user

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.frontend.ui.components.ChatInfo
import com.example.frontend.ui.components.CustomDialog


@Composable
@ExperimentalMaterial3Api
fun Chat(navController: NavController){
    var state by remember{
        mutableStateOf(false)
    }
    Box {
        Column(){
            ChatInfo(navController)
            ChatInfo(navController)
            ChatInfo(navController)
        }
        Box(){
            Button(onClick = {state=!state}) {
                Text("test")
            }
            if(state){
                CustomDialog(
                    onDismissRequest = { state=false },
                    onConfirmation = { state=false},
                    dialogTitle = "테스트용",
                    dialogText = "확인?"
                )
            }
        }

    }
}