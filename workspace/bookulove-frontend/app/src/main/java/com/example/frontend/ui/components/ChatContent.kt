package com.example.frontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun ChatContent(userName:String, userChat:String){

    CompositionLocalProvider(LocalLayoutDirection provides when(userName) {
        "나" ->LayoutDirection.Rtl
        else ->LayoutDirection.Ltr
    }) {
        Box(modifier= Modifier
            .fillMaxWidth()
            .padding()){
            Row(){
                Box(){
                    Avatar(40)
                }
                Box(modifier= Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(0.8f)){
                    Column(){
                        Text(text = userName)
                        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 5.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(when(userName){ "나" -> Color.Yellow else ->Color.White})
                                    .padding(8.dp)
                            ) {
                                Text(text = userChat)
                            }
                        }

                    }
                }

            }

        }
    }
}