package com.example.frontend.ui.screens.book

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.frontend.R
import com.example.frontend.ui.components.BookReportInfo
import com.example.frontend.ui.components.ChatContent
import com.example.frontend.ui.vo.bookList
import com.example.frontend.ui.vo.chatDataList

@Composable
fun BookTotal(navController: NavController){
    Box(modifier=Modifier.fillMaxHeight()){
        LazyColumn(){
            itemsIndexed(bookList) { index, item->
                BookReportInfo(navController, index);
            }
        }
        FloatingActionButton(onClick = { /*TODO*/ }, modifier=Modifier.align(Alignment.BottomEnd).padding(bottom = 40.dp, end=20.dp)) {
            Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "add book report")
        }
    }

}