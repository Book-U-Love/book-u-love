package com.example.frontend.ui.screens.user

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontend.data.model.EnterChatRoomData
import com.example.frontend.ui.components.ChatContent
import com.example.frontend.ui.components.ChatInput
import com.example.frontend.ui.theme.SkyBlue
import com.example.frontend.ui.vo.chatDataList
import com.example.frontend.viewmodel.ChatViewModel
import com.example.frontend.viewmodel.MainViewModel
import com.example.frontend.viewmodel.StompViewModel
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


@ExperimentalMaterial3Api
@Composable
fun ChatRoom(sellerId: String,mainViewModel: MainViewModel,chatViewModel: ChatViewModel,stompViewModel: StompViewModel){
    DisposableEffect(Unit){
        //stomp 호출 예정
        chatViewModel.changeInChatRoom()
        chatViewModel.enterChatRoom(sellerId.toInt())

        var roomId = chatViewModel.enterChatRoomData.value?.roomId
        stompViewModel.runStomp("/sub/room/$roomId")
        onDispose {
            stompViewModel.disconnect()
        }

    }
    val chatData = chatViewModel.enterChatRoomData.value;
    val targetName = chatData?.targetName.toString()
    Log.d("chatData", chatViewModel.enterChatRoomData.value.toString())
    Log.d("targetName", chatData?.targetName.toString())
    Log.d("chatlist", chatData?.chattingInfoDomainList.toString())
    mainViewModel.changeState(targetName)
    Surface(color= SkyBlue, modifier=Modifier.fillMaxSize()){

        Column(verticalArrangement = Arrangement.SpaceBetween){
            Box(modifier= Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .padding(bottom = 10.dp)) {
                LazyColumn() {
                    chatData?.chattingInfoDomainList?.let { chattingInfoList ->
                        itemsIndexed(chattingInfoList) { index, item ->
                            ChatContent(item, chatData.targetName)
                        }
                    }
                }
            }
            ChatInput(stompViewModel,chatViewModel)
        }

    }

}