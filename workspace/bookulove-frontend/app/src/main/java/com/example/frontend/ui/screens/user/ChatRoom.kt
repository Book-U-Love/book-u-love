package com.example.frontend.ui.screens.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.frontend.ui.components.ChatContent
import com.example.frontend.ui.components.ChatInput
import com.example.frontend.ui.theme.SkyBlue
import com.example.frontend.ui.vo.chatDataList


@ExperimentalMaterial3Api
@Composable
fun ChatRoom(){
    Surface(color= SkyBlue, modifier=Modifier.fillMaxSize()){

        Column(verticalArrangement = Arrangement.SpaceBetween){
            Box(modifier=Modifier.fillMaxWidth().fillMaxHeight(0.9f)) {
                LazyColumn(){
                    itemsIndexed(chatDataList) { index, item ->
                        ChatContent(item.userName, item.userChat)}
                }
            }
            ChatInput()
        }

    }

}