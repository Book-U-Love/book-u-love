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
import com.example.frontend.viewmodel.UserViewModel
import kotlinx.coroutines.delay
import java.lang.Exception

@Composable
@ExperimentalMaterial3Api
fun Chat(navController: NavController,mainViewModel: MainViewModel,stompViewModel: StompViewModel, chatViewModel: ChatViewModel,userViewModel: UserViewModel){
    DisposableEffect(Unit){
        var userId:Int = userViewModel.userMyInfo.value.get("userId")!!.toInt()
        stompViewModel.runStomp("/sub/$userId")
        chatViewModel.getChatRoomList()
        onDispose {
            stompViewModel.disconnect()
        }
    }
    var chatRoomList = chatViewModel.chatRoomList.value
    Box {
        Log.d("size",chatViewModel.chatRoomList.value?.size.toString())
        LazyColumn(){
           itemsIndexed(chatViewModel.chatRoomList.value){
               index, item -> ChatInfo(navController, item, chatViewModel)
           }
        }
    }
}