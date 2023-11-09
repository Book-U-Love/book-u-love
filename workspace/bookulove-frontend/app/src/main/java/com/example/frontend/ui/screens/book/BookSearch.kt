package com.example.frontend.ui.screens.book

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.frontend.R
import com.example.frontend.ui.vo.categoryList



@OptIn(ExperimentalFoundationApi::class)
@Composable
@ExperimentalMaterial3Api
fun BookSearch(navController:NavController){
    val categoryState = rememberLazyListState();

    Box(modifier=Modifier.fillMaxSize()){
       Column(){
           Box(){
               Row(modifier= Modifier
                   .fillMaxWidth()
                   .padding(10.dp),
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.Center){
                   Box(modifier=Modifier.fillMaxWidth(0.7f)){
                       OutlinedTextField(value = "", onValueChange = {},
                       modifier=Modifier.border(0.dp, Color.White),
                       )
                   }
                   Box(modifier=Modifier.fillMaxWidth(0.5f)){
                       Icon(modifier=Modifier.align(Alignment.Center),painter = painterResource(id = R.drawable.baseline_search_24), contentDescription ="searchIcon")
                   }
               }
           }
           Divider()
           Box(modifier=Modifier.padding(top=5.dp, bottom=5.dp)){
               LazyRow(state = categoryState){
                   itemsIndexed(categoryList){
                       index, item ->
                       Box(modifier=Modifier.padding(start=5.dp, end=5.dp)){
                           TextButton(
                               onClick = {
                                         },
                               modifier= Modifier

                           ) {
                                Text(text=item,color=Color.Black.copy(alpha=1f), fontSize = 18.sp)
                           }
                       }
                   }
               }
           }

           Divider()
       }
        // 책 추가 버튼
//        Box(modifier= Modifier
//            .align(Alignment.BottomEnd)
//            .padding(bottom=50.dp,end = 20.dp)){
//            FloatingActionButton(onClick = { /*TODO*/ }) {
//                Icon(painter = painterResource(R.drawable.baseline_add_24), contentDescription ="addBook" )
//            }
//        }
        FloatingActionButton(onClick = {navController.navigate("booktransactionregist")}, modifier=Modifier.align(Alignment.BottomEnd).padding(bottom = 40.dp, end=20.dp)) {
            Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "add book report")
        }
    }
}