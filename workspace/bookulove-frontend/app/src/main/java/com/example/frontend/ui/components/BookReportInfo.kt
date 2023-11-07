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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.frontend.R
import com.example.frontend.ui.vo.bookInfo
import com.example.frontend.ui.vo.bookList

@Composable
fun BookReportInfo(navController: NavController, index:Int){
    val book by remember{
        mutableStateOf(bookList.get(index))
    }
    Column(modifier=Modifier.clickable{navController.navigate("reportdetail/$index"); Log.d("test",index.toString())}){
        Row(modifier= Modifier.padding(top=15.dp, bottom=15.dp,start=5.dp, end=5.dp)){
            Box(modifier=Modifier.padding(30.dp)){
                Image(painter = painterResource(id = R.drawable.mark), contentDescription = "BookImage")
            }
            Box {
                Column {
                    Box(modifier=Modifier.padding(bottom=10.dp)){
                        Text(text = book.bookName, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                    Box {
                        Text(text = book.bookReporter)
                    }
                    Box{
                        Text(text = "작가")
                    }
                }
            }

            Box(modifier=Modifier.fillMaxWidth()){
                Text(text = book.bookReportDate ,modifier=Modifier.align(Alignment.BottomEnd))
            }

        }
        Divider()
    }

}