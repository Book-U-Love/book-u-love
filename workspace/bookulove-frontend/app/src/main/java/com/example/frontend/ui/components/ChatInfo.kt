package com.example.frontend.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ChatInfo(){
    Surface(border = BorderStroke(1.dp, Color.Black), modifier = Modifier.padding(10.dp)){
        Box(modifier = Modifier.fillMaxWidth()){
            Column {
                Box(){
                    Avatar()
                }
                Box{
                    Row(){
                        Text("user_nickname")
                        Text("user_chat")
                    }
                }
                Box{
                    BadgedBox(badge = {
                        Badge{
                            val number = 0
                            Text(number.toString(), modifier=Modifier.semantics { contentDescription="notifications" })
                        }
                    }) {

                    }
                }
            }

        }
    }

}