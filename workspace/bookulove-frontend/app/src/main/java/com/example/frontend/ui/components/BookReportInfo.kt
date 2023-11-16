package com.example.frontend.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.frontend.R
import com.example.frontend.ui.vo.bookInfo
import com.example.frontend.ui.vo.bookList

@Composable
fun BookReportInfo(navController: NavController, report: Map<String, String>){
//    val book by remember{
//        mutableStateOf(bookList.get(index))
//    }
//    Log.d("find img", report.toString())
    Column(modifier=Modifier.clickable{navController.navigate("reportdetail/${report.get("reviewId").toString()}")}){
        Row(modifier= Modifier.padding(top=15.dp, bottom=15.dp,start=5.dp, end=5.dp)){
            Box(modifier=Modifier.padding(30.dp)){
                Image(painter = rememberImagePainter(report.get("bookCover").toString()),
                    contentDescription = "BookImage",
                    contentScale = ContentScale.Fit)
            }
            Box {
                Column {
                    Box(modifier=Modifier.padding(bottom=10.dp)){
                        Text(text = report.get("bookTitle").toString(), fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                    Box {
                        Text(text = report.get("userName").toString())
                    }
                }
            }

            Box(modifier=Modifier.fillMaxWidth()){
                Text(text = report.get("createdTime").toString() ,modifier=Modifier.align(Alignment.BottomEnd))
            }

        }
        Divider()
    }

}