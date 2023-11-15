package com.example.frontend.ui.screens.book

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.frontend.data.model.ModifyPw
import com.example.frontend.ui.components.BookInfo
import com.example.frontend.ui.components.FuncBtn
import com.example.frontend.ui.components.InputField
import com.example.frontend.ui.components.Message
import com.example.frontend.viewmodel.AuthViewModel
import com.example.frontend.viewmodel.BookViewModel
import com.example.frontend.viewmodel.UserViewModel

@Composable
@ExperimentalMaterial3Api
fun BookList(bookViewModel: BookViewModel){
    LaunchedEffect(key1 = Unit){
        bookViewModel.getBookList(sale = false, borrow = false)
    }
    val bookList = bookViewModel.bookList
    Log.d("find booklist", bookList.toString())
    if(bookList.value.isNotEmpty()){
        Box(modifier = Modifier.fillMaxSize()){
            LazyColumn(){
                for(book in bookList.value){
                    item{
                        BookInfo()
                    }
                }

            }
        }
    }
}