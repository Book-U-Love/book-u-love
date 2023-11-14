package com.example.frontend.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ReviewCard(
    content: String
){
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(80.dp)
            .border(width = 1.dp, color = Color.Black),
        ){
            Text(
                text = content,
                modifier = Modifier.padding(start = 5.dp, top = 5.dp)
            )
    }
    Spacer(modifier = Modifier.height(40.dp))
}