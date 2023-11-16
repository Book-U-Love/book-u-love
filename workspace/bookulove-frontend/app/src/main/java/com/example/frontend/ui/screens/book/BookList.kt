package com.example.frontend.ui.screens.book

import android.graphics.fonts.FontStyle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun BookList(bookViewModel: BookViewModel, userId: String = ""){
    val sale = remember{ mutableStateOf(false) }
    val borrow = remember{ mutableStateOf(false) }
    val dialog = remember{ mutableStateOf(false) }
    var bookDetail:Map<String, String> by remember{ mutableStateOf(mapOf()) }
    LaunchedEffect(Unit, sale.value, borrow.value){
        if(userId == ""){
            bookViewModel.getBookList(sale = sale.value, borrow = borrow.value)
        } else{
            bookViewModel.getUserBookList(userId, sale = sale.value, borrow = borrow.value)
        }
    }
    val bookList = bookViewModel.bookList
    if(bookList.value.isNotEmpty()){
        Box(modifier = Modifier.fillMaxSize()){

            LazyColumn(){
                item{
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        FuncBtn(name = "전체보기", onClick = {
                            sale.value = false
                            borrow.value = false
                        })
                        FuncBtn(name = "판매", onClick = {
                            sale.value = !sale.value
                        })
                        FuncBtn(name = "대여", onClick = {
                            borrow.value = !borrow.value
                        })
                    }
                }
                for(book in bookList.value){
                    item{
                        BookInfo(book, onClick = {
                            dialog.value = true
                            bookDetail = book
                        })
                    }
                }

            }
        }
        if(dialog.value){
            BookDetail(
                book= bookDetail,
                onDismissRequest = {dialog.value = false},
                onConfirmation = {
                    dialog.value = false
                }
            )
        }
    }
}

@Composable
fun BookDetail(
    book: Map<String, String>,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
){
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card (
            modifier = Modifier.fillMaxWidth()
        ) {
            Column (
                modifier = Modifier.fillMaxWidth().padding(4.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier = Modifier.height(20.dp))
                Text(book.get("title").toString(), modifier = Modifier.padding(5.dp), fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Text(book.get("author").toString(), modifier = Modifier.padding(5.dp), fontWeight = FontWeight.Bold)
                    Text(book.get("price").toString(), modifier = Modifier.padding(5.dp), fontWeight = FontWeight.Bold)
                    Text(book.get("condition").toString(), modifier = Modifier.padding(5.dp), fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.End
                ){
                    if(book.get("allowSale") == "true"){
                        Box(modifier = Modifier.padding(1.dp)){
                            Box(modifier = Modifier
                                .background(
                                    Color.Black.copy(alpha = 0.6f),
                                    RoundedCornerShape(5.dp)
                                )
                                .padding(3.dp)){
                                Text("판매중", color= Color.White)
                            }
                        }
                    }
                    if(book.get("allowBorrow") == "true"){
                        Box(modifier = Modifier.padding(1.dp)){
                            Box(modifier = Modifier
                                .background(
                                    Color.Black.copy(alpha = 0.6f),
                                    RoundedCornerShape(5.dp)
                                )
                                .padding(3.dp)){
                                if(book.get("isBorrow").toString() == "true"){
                                    Text("대여중", color = Color.White)
                                } else{
                                    Text("대여가능", color = Color.White)
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    book.get("details").toString(),
                    modifier = Modifier.padding(5.dp).height(70.dp),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                FuncBtn(name = "닫기", onClick = { onDismissRequest() })
                Spacer(modifier = Modifier.width(10.dp))
                FuncBtn(name = "채팅하기", onClick = { onConfirmation() })
                Spacer(modifier = Modifier.width(10.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}