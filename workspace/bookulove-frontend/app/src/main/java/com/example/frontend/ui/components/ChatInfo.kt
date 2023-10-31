package com.example.frontend.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Badge
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
@ExperimentalMaterial3Api
fun ChatInfo(navController: NavController){
    Surface(modifier=Modifier.clickable{navController.navigate("chatroom")}){
        Box(){

            Row(modifier= Modifier
                .height(100.dp)
                .fillMaxWidth()){
                Box(modifier=Modifier.fillMaxWidth(0.25f)){
                    Avatar(75)
                }
                Box(modifier=Modifier.fillMaxWidth(0.75f)){
                    Column(modifier=Modifier.fillMaxWidth()){
                        Text("김싸피",
                            modifier=Modifier.padding(top=15.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize=18.sp)
                        Text("채팅 내용을 입력하세요.",
                            modifier=Modifier.padding(top=8.dp))
                    }
                }
                Box(modifier= Modifier
                    .fillMaxWidth()
                    .height(100.dp), contentAlignment = Alignment.TopCenter){
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier= Modifier.padding(top=20.dp)){
                        Text(fontSize = 8.sp,text="2023-10-26")
                        Badge(modifier= Modifier
                            .padding(top = 15.dp)
                            .size(24.dp)){
                            val number = 0
                            Text(
                                number.toString(),
                                modifier = Modifier.semantics { contentDescription = "notifications" })
                        }
                    }

                }
            }

        }

    }
    Divider()
}
