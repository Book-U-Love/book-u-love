package com.example.frontend.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.frontend.R


@Composable
fun Avatar(){
    Image(painter = painterResource(id = R.drawable.user_default), contentDescription = "user_default")
}