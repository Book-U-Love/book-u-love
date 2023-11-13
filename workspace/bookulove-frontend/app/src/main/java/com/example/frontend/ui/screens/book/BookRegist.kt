package com.example.frontend.ui.screens.book

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.frontend.ui.components.DropDown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookRegist(navController: NavController){
    Column(modifier=Modifier.padding(15.dp)){
        Box {
            Text("ISBN")
        }
        Box {
            OutlinedTextField(value = "", onValueChange = {})
        }
        Box{
            Text("책 상태")
        }
        Box {
           DropDown(itemList = listOf("매우 좋음","좋음","보통","나쁨","매우 나쁨"))
        }
        Box {
            Text("거래 여부")
        }
        Box{
            Row {
                Button(onClick = { /*TODO*/ }) {
                    Text("판매")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text("대여")
                }
            }

        }
        Box {
            Text("추가사항")
        }
        Box{
            OutlinedTextField(value = "", onValueChange = {},modifier= Modifier
                .fillMaxWidth()
                .height(300.dp))
        }
        Box{
            Button(onClick = { /*TODO*/ },modifier=Modifier.fillMaxWidth()) {
                Text("등록")
            }
        }
    }
}