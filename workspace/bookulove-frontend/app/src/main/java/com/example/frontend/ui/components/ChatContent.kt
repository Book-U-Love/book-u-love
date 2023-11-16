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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.frontend.data.model.EnterChatInfoDomain

@Composable
fun ChatContent(item:EnterChatInfoDomain, targetName:String){

    CompositionLocalProvider(LocalLayoutDirection provides when(item.writerName==targetName) {
        false ->LayoutDirection.Rtl
        true ->LayoutDirection.Ltr
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
                        Text(text = item.writerName)
                        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 5.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(when(item.writerName==targetName){ false -> Color.Yellow true ->Color.White})
                                    .padding(8.dp)
                            ) {
                                Text(text = item.content)
                            }
                        }

                    }
                }

            }

        }
    }
}