//package com.example.frontend.ui.screens.book
//
//import android.util.Log
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.Card
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.window.Dialog
//import com.example.frontend.data.model.ModifyPw
//import com.example.frontend.ui.components.BookInfo
//import com.example.frontend.ui.components.FuncBtn
//import com.example.frontend.ui.components.InputField
//import com.example.frontend.ui.components.Message
//import com.example.frontend.viewmodel.AuthViewModel
//import com.example.frontend.viewmodel.BookViewModel
//import com.example.frontend.viewmodel.UserViewModel
//
//@Composable
//@ExperimentalMaterial3Api
//fun BookList(bookViewModel: BookViewModel){
//    LaunchedEffect(key1 = Unit){
//        bookViewModel.getBookList(sale = false, borrow = false)
//    }
//    val bookList = bookViewModel.bookList
//    val openMsg = remember{ mutableStateOf(false) }
//    Box(modifier = Modifier.fillMaxSize()){
//        LazyColumn(){
//            for(book in bookList.value){
//                item{
//                    BookInfo(book)
//                }
//            }
//            item{
//                FuncBtn(name = "도서 등록", onClick = { openMsg.value = true })
//            }
//        }
//        if(openMsg.value){
//            RegistBook(
//                onDismissRequest = { openMsg.value = false },
//                onConfirmation = { openMsg.value = false },
//                bookViewModel = bookViewModel
//            )
//        }
//    }
//}
//
//@ExperimentalMaterial3Api
//@Composable
//fun RegistBook(
//    onDismissRequest: () -> Unit,
//    onConfirmation: () -> Unit,
//    bookViewModel: BookViewModel
//){
//    val modifyPw: ModifyPw = ModifyPw("", "")
//    var isbn by remember { mutableStateOf("") }
//    var title by remember { mutableStateOf("") }
//    var author by remember { mutableStateOf("") }
//    Dialog(onDismissRequest = { onDismissRequest() }) {
//        Card (
//            modifier = Modifier.fillMaxWidth(),
//        ){
//            Column (
//                modifier = Modifier.fillMaxWidth(),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ){
//                InputField(value = isbn, label = "ISBN", onValueChanged = {isbn = it})
//                InputField(value = title, label = "제목", onValueChanged = {}, enable = false)
//                InputField(value = author, label = "저자", onValueChanged = {}, enable = false)
//                FuncBtn(name = "검색", onClick = {
//                    bookViewModel.getBookInfo(isbn)
//                })
//            }
//        }
//    }
//}