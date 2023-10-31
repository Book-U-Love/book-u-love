package com.example.frontend.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.example.frontend.ui.vo.chatDataList
import com.example.frontend.ui.vo.chatList


@Composable
fun ChatInput(){
    var text by remember{
        mutableStateOf(TextFieldValue());
    }
    var state = rememberScrollState()
    var lineCount by remember{
        mutableStateOf(1)
    }
    Surface(modifier = Modifier.fillMaxWidth()){
        Box(){
            Row(verticalAlignment = Alignment.CenterVertically){

                TextField(
                    value = text,
                    onValueChange = {
                        text = it
                        lineCount = it.text.count { it == '\n' } + 1;
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .then(Modifier.verticalScroll(state))
                )
                Box(){
                    Button(modifier=Modifier.fillMaxWidth(),
                        onClick = {
                            chatDataList.add(chatList("나",text.text))
                            Log.d("text",text.text)
                            Log.d("size", chatDataList.toString())
                            }, shape = RoundedCornerShape(0)) {
                        Text("전송")
                    }
                }

            }

        }
    }



}