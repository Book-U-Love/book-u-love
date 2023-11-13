package com.example.frontend.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.frontend.R
import com.example.frontend.ui.theme.SkyBlue
import com.example.frontend.ui.vo.bookList
import com.example.frontend.viewmodel.BookViewModel
import java.util.Date

@Composable
fun BookReportRegist(navController: NavController){
    var isbn = remember{
        mutableStateOf("")
    }
    var bookTitle = remember{
        mutableStateOf("")
    }
    var reportTitle = remember{
        mutableStateOf("")
    }
    var reportContent = remember{
        mutableStateOf("")
    }

    val viewModel = BookViewModel();
    Column {
        Box(modifier=Modifier.padding(15.dp)){
            Text(text = "책 제목")
        }
        Divider()
        Box(modifier=Modifier.padding(15.dp)){
            Text(text = bookTitle.value)
        }
        Divider()
        Box(modifier=Modifier.padding(15.dp)){
            Text(text= "나")
        }
        Divider()
        Box(){
            BasicTextField(value = reportContent.value,
                onValueChange = {text-> reportContent.value = text},
                modifier= Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .padding(15.dp),
                textStyle = TextStyle(fontSize = 18.sp),
            )
        }
    }

    Box(modifier= Modifier
        .fillMaxHeight()
        .fillMaxWidth()){
        TextButton(onClick = { Log.d("등록","등록")},
            modifier= Modifier
                .align(Alignment.BottomCenter)
                .width(350.dp)
                .height(60.dp)
                .background(SkyBlue, RoundedCornerShape(15.dp))
        ) {
            Text(text = "독후감 등록", color = Color.White
                , fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
    }

}