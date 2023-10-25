package com.example.frontend.ui.screens.user

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontend.ui.components.ChatInfo

@Composable
fun Chat(){
    Box(modifier = Modifier.padding(10.dp)){
        ChatInfo()
    }
}