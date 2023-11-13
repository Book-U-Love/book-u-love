package com.example.frontend.ui.screens.book

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.frontend.ui.components.BookInfo
import com.example.frontend.ui.components.BookReportInfo
import com.example.frontend.ui.components.CustomFAB
import com.example.frontend.ui.vo.bookList
import com.example.frontend.viewmodel.BookViewModel

@Composable
fun BookTotal(navController: NavController, bookViewModel: BookViewModel){
    Box(modifier=Modifier.fillMaxHeight()){
        Column {
            Box(modifier=Modifier.fillMaxWidth()){
                Text("보유 도서 목록",modifier=Modifier.padding(15.dp), fontSize = 18.sp)
            }
            Box {
                BookInfo()
            }
            Divider()
            Box(modifier=Modifier.fillMaxWidth()){
                Text("내 독후감 목록",modifier=Modifier.padding(15.dp), fontSize = 18.sp)
            }
            Divider()
            Box {
                LazyColumn(){
                    itemsIndexed(bookList) { index, item->
                        BookReportInfo(navController, index);
                    }
                }
            }
        }


//        FloatingActionButton(onClick = { /*TODO*/ }, modifier= Modifier
//            .align(Alignment.BottomEnd)
//            .padding(bottom = 40.dp, end = 20.dp)) {
//            Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "add book report")
//        }

        CustomFAB(modifier= Modifier
            .align(Alignment.BottomEnd)
            .padding(bottom = 40.dp, end = 20.dp), navController)
    }

}