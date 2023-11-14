package com.example.frontend.ui.screens.book

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.frontend.R
import com.example.frontend.ui.components.BookInfoCard
import com.example.frontend.ui.vo.categoryList
import com.example.frontend.viewmodel.BookViewModel


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
        FloatingActionButton(onClick = {navController.navigate("booktransactionregist")}, modifier= Modifier
            .align(Alignment.BottomEnd)
            .padding(bottom = 40.dp, end = 20.dp)) {
            Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "add book report")
        }
    }
}
@ExperimentalMaterial3Api
@Composable
fun BookIsbnSearch(){
    var isbn = remember{
        mutableStateOf(TextFieldValue("9791192300818"))
    }
    val bookViewModel:BookViewModel = BookViewModel()
    Box(modifier=Modifier.padding(20.dp)){
        Column {
            Box(){
                Text("ISBN", fontSize = 24.sp)
            }
            Box(){
                Text("ISBN 13자리를 입력해주세요.", fontSize = 18.sp, color=Color.Black.copy(alpha = 0.5f))
            }
            Box(){
                Row(modifier= Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .drawBehind {
                        drawLine(
                            Color.Black.copy(alpha = 0.6f),
                            Offset(0f, size.height),
                            Offset(size.width, size.height),
                            3.dp.toPx()
                        )
                    },
                    verticalAlignment = Alignment.CenterVertically
                    ){
                    BasicTextField(value = isbn.value, onValueChange = {it->isbn.value=it; Log.d("text",isbn.value.toString())},
                        modifier= Modifier
                            .fillMaxWidth(0.8f)
                            .padding(start = 15.dp, end = 15.dp),
                        ) {
                        Text(text = isbn.value.text, textAlign = TextAlign.Start, style = TextStyle(Color.Black, fontSize = 21.sp))
                    }
                    IconButton(onClick = {  bookViewModel.bookSearch(isbn.value.text)}) {
                        Icon(painter = painterResource(id = R.drawable.baseline_search_24), contentDescription = "isbnsearch" )
                    }
                }
            }
            Box(modifier=Modifier.padding(top=30.dp, start=15.dp, end=15.dp)){
                Column {
                    Box(){
                        Text("검색 결과 출력")
                    }
                    Box {
                        BookInfoCard()
                    }
                }
            }
        }
    }

}