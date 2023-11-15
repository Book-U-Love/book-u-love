package com.example.frontend.ui.screens.user

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.frontend.viewmodel.MainViewModel
import com.example.frontend.viewmodel.StompViewModel
import kotlinx.coroutines.delay
import java.lang.Exception

@Composable
@ExperimentalMaterial3Api
fun Chat(navController: NavController,mainViewModel: MainViewModel,stompViewModel: StompViewModel){
    val bookApi: BookApi = API.getInstance().create(BookApi::class.java)
    var state by remember{
        mutableStateOf(false)
    }
    LaunchedEffect(Unit){
        try{
            stompViewModel.init()
            delay(5000)
            stompViewModel.connect()
            delay(5000)
            stompViewModel.sendMessage("asdfasdf")
            Log.d("accessToken", PrefsRepository().getValue("accessToken"))
        }catch (e:Exception){
            Log.d("test","eror")
        }

    }
    Log.d("asdf",navController.currentBackStackEntry?.destination.toString())
    Box {
        Column(){
            ChatInfo(navController)
            ChatInfo(navController)
            ChatInfo(navController)
        }
    }
    Button(onClick = { stompViewModel.sendMessage("asdfasdfadsfadsf") }) {
        Text("send")
    }
}