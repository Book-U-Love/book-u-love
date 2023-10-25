package com.example.frontend.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun Home(){
    Box(modifier= Modifier.fillMaxWidth()){
        Text(text = "Home")
    }
}