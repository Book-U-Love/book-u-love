package com.example.frontend.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberImagePainter
import com.example.frontend.R
import com.example.frontend.viewmodel.BookViewModel
import com.example.frontend.viewmodel.UserViewModel

@Composable
fun BookInfo(userViewModel: UserViewModel, book: Map<String, String> = mapOf(), onClick: () -> Unit = {}, canModify: Boolean = false , bookViewModel: BookViewModel = BookViewModel()){
    val dialog = remember{ mutableStateOf(false) }
    Surface {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
        ){
            Box(modifier= Modifier
                .padding(15.dp)){
               Image(modifier=Modifier.size(100.dp),painter = rememberImagePainter(book.get("cover").toString()), contentDescription = "BookImage")
            }
            Box(){
                Column(modifier=Modifier.padding(start=15.dp,top=20.dp,end=15.dp, bottom = 10.dp)){
                    Box(){
                        Text(book.get("title").toString(), fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                    Box(modifier=Modifier.padding(top=3.dp, bottom=3.dp)){
                        Row {
                            Text(book.get("createdTime").toString(), fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(start = 5.dp))
                            }
                    }
                    Row(){
                        if(!canModify && book.get("sellerId").toString() == userViewModel.userMyInfo.value.get("userId").toString()){
                            Text("보유중인 도서입니다", color = Color.Red)
                        } else{
                            if(book.get("allowSale") == "true"){
                                Box(modifier = Modifier.padding(1.dp)){
                                    Box(modifier = Modifier
                                        .background(
                                            Color.Black.copy(alpha = 0.6f),
                                            RoundedCornerShape(5.dp)
                                        )
                                        .padding(3.dp)){
                                        Text("판매중", color=Color.White)
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

                    }
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        Box(modifier=Modifier.fillMaxWidth()){
                                Row {
                                    if(canModify && book.get("sellerId").toString() == userViewModel.userMyInfo.value.get("userId").toString()){
                                        if(canModify){
                                            ElevatedButton(
                                                modifier= Modifier
                                                    .padding(start = 10.dp)
                                                    .width(80.dp)
                                                    .height(40.dp),
                                                shape = RoundedCornerShape(5.dp),
                                                onClick = {
                                                    dialog.value = true
                                                }){
                                                Text("수정")}
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    if(dialog.value){
        BookModify(
            book = book,
            onDismissRequest = { dialog.value = false },
            onConfirmation = {
                dialog.value = false
                bookViewModel.getMyBookList()
             },
            bookViewModel = bookViewModel
        )
    }
    Divider(color=Color.Black.copy(alpha=0.3f))
}

@Composable
fun BookModify(
    book: Map<String, String>,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    bookViewModel: BookViewModel
){
    val allowSale = remember{ mutableStateOf(book.get("allowSale").toString()) }
    val allowBorrow = remember{ mutableStateOf(book.get("allowBorrow").toString()) }
    val isBorrow = remember{ mutableStateOf(book.get("isBorrow").toString()) }
    val isRemoved = remember{ mutableStateOf("false") }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card (
            modifier = Modifier.fillMaxWidth()
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween){
                    Button(onClick = {
                        allowSale.value = "true"
                        isRemoved.value = "false"
                    }){
                        Text("판매중")
                    }
                    Button(onClick = {
                        allowSale.value = "false"
                        isRemoved.value = "true"
                    }){
                       Text("판매완료")
                    }
                }
                Row(horizontalArrangement = Arrangement.SpaceBetween){
                    Button(onClick = {
                        allowBorrow.value = "true"
                        isBorrow.value = "false"
                    }){
                        Text("대여가능")
                    }
                    Button(onClick = {
                        allowBorrow.value = "true"
                        isBorrow.value = "true"
                    }){
                        Text("대여중")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween){
                    FuncBtn(name = "취소", onClick = { onDismissRequest() })
                    FuncBtn(name = "수정", onClick = {
                        val result: Map<String, String> =
                            mapOf(
                                "buid" to book.get("buid").toString(),
                                "condition" to book.get("condition").toString(),
                                "allowSale" to allowSale.value,
                                "isRemoved" to isRemoved.value,
                                "allowBorrow" to allowBorrow.value,
                                "isBorrow" to isBorrow.value,
                                "details" to book.get("details").toString())
                        Log.d("find result", result.toString())
                        bookViewModel.modifyBook(result)
                        onConfirmation()
                    })
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}