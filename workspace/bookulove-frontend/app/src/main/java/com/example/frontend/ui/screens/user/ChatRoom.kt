package com.example.frontend.ui.screens.user

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontend.ui.components.ChatContent
import com.example.frontend.ui.components.ChatInput
import com.example.frontend.ui.theme.SkyBlue
import com.example.frontend.ui.vo.chatDataList
import kotlinx.coroutines.launch


@ExperimentalMaterial3Api
@Composable
fun ChatRoom(){
    val lazyColumnListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Surface(color= SkyBlue, modifier=Modifier.fillMaxSize()){

        Column(verticalArrangement = Arrangement.SpaceBetween){
            Box(modifier= Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .padding(bottom = 10.dp)) {
                LazyColumn(state=lazyColumnListState){
                    coroutineScope.launch {
                        lazyColumnListState.scrollToItem(chatDataList.size)
                    }
                    itemsIndexed(chatDataList) { index, item ->
                        ChatContent(item.userName, item.userChat)}
                }
            }
            ChatInput()
        }

    }

}