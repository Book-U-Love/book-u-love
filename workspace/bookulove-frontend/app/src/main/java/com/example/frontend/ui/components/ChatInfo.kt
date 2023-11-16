package com.example.frontend.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Badge
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.frontend.data.model.ChattingRoomInfoDomainList
import com.example.frontend.viewmodel.ChatViewModel

@ExperimentalMaterial3Api
@Composable
fun ChatInfo(navController: NavController, item:ChattingRoomInfoDomainList,chatViewModel: ChatViewModel){
    var ready = chatViewModel.inChatRoom.value

    Surface(modifier=Modifier.clickable{
        chatViewModel.enterChatRoom(item.targetId)
        if(ready)navController.navigate("chatroom/${item.targetId}")
    }){
        Box(){

            Row(modifier= Modifier
                .height(100.dp)
                .fillMaxWidth()){
                Box(modifier=Modifier.fillMaxWidth(0.25f)){
                    Avatar(75)
                }
                Box(modifier=Modifier.fillMaxWidth(0.75f)){
                    Column(modifier=Modifier.fillMaxWidth()){
                        Text(item.targetName,
                            modifier=Modifier.padding(top=15.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize=18.sp)
                        Text(item.lastContent,
                            modifier=Modifier.padding(top=8.dp))
                    }
                }
                Box(modifier= Modifier
                    .fillMaxWidth()
                    .height(100.dp), contentAlignment = Alignment.TopCenter){
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier= Modifier.padding(top=20.dp)){
                        Text(fontSize = 8.sp,text=item.lastTime)
                        Badge(modifier= Modifier
                            .padding(top = 15.dp)
                            .size(24.dp)){
                            val number = item.unReadCount
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
