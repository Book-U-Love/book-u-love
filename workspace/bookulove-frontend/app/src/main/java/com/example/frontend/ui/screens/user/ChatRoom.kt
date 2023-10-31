package com.example.frontend.ui.screens.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.frontend.ui.components.ChatContent
import com.example.frontend.ui.components.ChatInput
import com.example.frontend.ui.theme.SkyBlue
import com.example.frontend.ui.vo.chatDataList


@ExperimentalMaterial3Api
@Composable
fun ChatRoom(){
    Surface(color= SkyBlue, modifier=Modifier.fillMaxSize()){

        Column(verticalArrangement = Arrangement.SpaceBetween){
            Box() {
                Column(){
                    chatDataList.forEach {
                        it -> ChatContent(it.userName, it.userChat);
                    }
                }
            }
            ChatInput()
        }

    }

}