package com.example.frontend.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontend.R

@ExperimentalMaterial3Api
@Composable
@Preview
fun BookInfoCard(){
//    Box(modifier= Modifier
//        .padding(top = 15.dp)
//        .fillMaxWidth()
//        .fillMaxHeight(0.4f)
//        .border(2.dp, Color.Black, RoundedCornerShape(5.dp))){
//        Row(){
//            Image(painter = painterResource(id = R.drawable.default_book_img), contentDescription ="",modifier= Modifier
//                .fillMaxHeight()
//                .fillMaxWidth(0.5f))
//            Box(modifier = Modifier.fillMaxSize()){
//
//            }
//        }
//    }

    ElevatedCard(onClick = { /*TODO*/ },modifier= Modifier
        .padding(top = 15.dp)
        .fillMaxWidth()
        .background(Color.White)
        .padding(15.dp)) {
        Image(painter = painterResource(id = R.drawable.default_book_img),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier= Modifier
                .fillMaxWidth()
                .background(Color.White))
        Text("마흔에 읽는 쇼펜하우어",modifier=Modifier.padding(30.dp), fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Box(modifier= Modifier
            .padding(start = 15.dp, bottom = 15.dp, end = 15.dp)
            .fillMaxWidth()){
            Column(modifier= Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, bottom = 15.dp)){
                Box(modifier=Modifier.fillMaxWidth()){
                    Text("인문학", fontSize = 18.sp)
                }
                Box(modifier=Modifier.fillMaxWidth()){
                    Text("강용수", fontSize=16.sp)
                }
                Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween){
                    Text("유노북스", fontSize = 16.sp)
                    Text("2023-09-07", fontSize = 16.sp)
                }


            }
        }

    }
}