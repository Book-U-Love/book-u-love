package com.example.frontend.ui.screens.book

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.frontend.ui.components.BookInfo

@Preview(showBackground = true)
@Composable
fun BookList(){
    var bookCnt = 5
    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(){
            for(i : Int in 1..bookCnt){
                item{
                    BookInfo()
                }
            }
        }
    }
}