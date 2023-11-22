package com.example.frontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontend.ui.theme.SkyBlue
import com.example.frontend.ui.vo.bookList
import com.example.frontend.viewmodel.BookViewModel

@ExperimentalMaterial3Api
@Composable
fun BookReportDetail(index:Int, bookViewModel: BookViewModel){
//    var report by remember{
//        mutableStateOf(bookList.get(index))
//    }

//    val viewModel = BookViewModel();
    LaunchedEffect(Unit){
        bookViewModel.getReport(index.toString())
    }
    val report = bookViewModel.report
    Column {
        Box(modifier=Modifier.padding(15.dp)){
            Text(text = report.value.get("userName").toString())
        }
        Divider()
        Box(modifier=Modifier.padding(15.dp)){
            Text(text= report.value.get("bookTitle").toString())
        }
        Divider()
        Box(){
           BasicTextField(value = report.value.get("reviewContent").toString(),
//               onValueChange = {text-> report = report.copy(bookReportContent = text)},
               onValueChange = {},
               modifier= Modifier
                   .fillMaxWidth()
                   .fillMaxHeight(0.9f)
                   .padding(15.dp),
               textStyle = TextStyle(fontSize = 18.sp),
               readOnly=bookViewModel.reportState.value,
               )
           }
        }

//        Box(modifier= Modifier
//            .fillMaxHeight()
//            .fillMaxWidth()){
//            TextButton(onClick = {
//                when(bookViewModel.reportState.value){
//                    true-> bookViewModel.changeState()
//                    false-> {
//                        bookViewModel.changeState();
////                        bookList.set(index, report)
//                    }
//                };
//
//                },
//                modifier= Modifier
//                    .align(Alignment.BottomCenter)
//                    .width(350.dp)
//                    .height(60.dp)
//                    .background(SkyBlue, RoundedCornerShape(15.dp))
//            ) {
//                Text(text = when(bookViewModel.reportState.value){true -> "수정하기" false -> "수정 완료" } , color = androidx.compose.ui.graphics.Color.White
//                , fontWeight = FontWeight.Bold, fontSize = 18.sp)
//            }
//        }


}

