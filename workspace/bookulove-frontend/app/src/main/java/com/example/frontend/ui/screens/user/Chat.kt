package com.example.frontend.ui.screens.user

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.frontend.data.api.API
import com.example.frontend.data.api.BookApi
import com.example.frontend.data.repository.PrefsRepository
import com.example.frontend.ui.components.ChatInfo
import com.example.frontend.viewmodel.ChatViewModel
import com.example.frontend.viewmodel.MainViewModel
import com.example.frontend.viewmodel.StompViewModel
import kotlinx.coroutines.delay
import java.lang.Exception

@Composable
@ExperimentalMaterial3Api
fun Chat(navController: NavController,mainViewModel: MainViewModel,stompViewModel: StompViewModel, chatViewModel: ChatViewModel){
    DisposableEffect(Unit){
        stompViewModel.runStomp()
        try{
            chatViewModel.getChatRoomList()
            Log.d("채팅방리스트", "로드 성공")

        }catch (e:Exception){
            Log.d("채팅방리스트", "로드 실패")
        }
        onDispose {
            stompViewModel.disconnect()
        }
    }
    Log.d("asdf",navController.currentBackStackEntry?.destination.toString())
    Log.d("채팅방 리스트1111111", chatViewModel.chatRoomList.value.toString())
    Box {
        Column(){
            ChatInfo(navController)
            ChatInfo(navController)
            ChatInfo(navController)
        }
        LazyColumn(){
//            items(chatViewModel.chatRoomList.value!!.size){
//                ChatInfo(navController = )
//            }
        }
    }
}