package com.example.frontend.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.frontend.R
import com.example.frontend.data.model.BookReportReq
import com.example.frontend.ui.theme.SkyBlue
import com.example.frontend.ui.vo.Routes
import com.example.frontend.ui.vo.bookList
import com.example.frontend.viewmodel.BookViewModel
import kotlinx.coroutines.delay
import java.util.Date

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BookReportRegist(bookViewModel: BookViewModel,navController: NavController){
    var reportTitle = remember{
        mutableStateOf("")
    }
    var reportContent = remember{
        mutableStateOf("")
    }
    val searchResult by bookViewModel.bookSearchRes.collectAsState()
    val registResult by bookViewModel.reportRegistRes.collectAsState()
    if(registResult == true){
        Log.d("성공했는데","왜 안뜸?")
        CustomDialog(
            onConfirmation = { Log.d("success", "독후감 등록 완료") },
            dialogTitle = "독후감 등록 완료!",
            dialogText = "등록이 완료되었습니다.",
            dialogColor = Color.Blue.copy(alpha = 0.5f),
        )
    }else if(registResult == false){
        Log.d("실패함","ㅋㅋㅁㄴㅇ")
        CustomDialog(
            onConfirmation = { Log.d("fail", "독후감 등록 실패") },
            dialogTitle = "독후감 등록 실패",
            dialogText = "등록에 실패했습니다.",
            dialogColor = Color.Red.copy(alpha = 0.5f),
        )
    }
    LaunchedEffect(registResult==true){
        delay(3000)
        bookViewModel.resetRegistRes()
        if(registResult==true) navController.navigate(Routes.MYLIBRARY)
    }
    Column {
        Box(modifier=Modifier.padding(30.dp, 15.dp)){
            Text(searchResult!!.getValue("title"))
        }
        Divider()
        Box(){
            BasicTextField(value = reportTitle.value,
                onValueChange = {text-> reportTitle.value = text},
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp),
                textStyle = TextStyle(fontSize = 18.sp),
            ){innerTextField ->
                TextFieldDefaults.TextFieldDecorationBox(
                    value = reportTitle.value,
                    innerTextField = innerTextField,
                    enabled = true,
                    singleLine = false,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = MutableInteractionSource(),
                    placeholder = {
                        if(reportTitle.value=="")Text("제목을 입력해주세요.")
                    }
                ) {

                }
            }
        }
        Divider()
        Box(modifier=Modifier.padding(30.dp, 15.dp)){
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
            ){
                    innerTextField ->
                TextFieldDefaults.TextFieldDecorationBox(
                    value = reportContent.value,
                    innerTextField = innerTextField,
                    enabled = true,
                    singleLine = false,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = MutableInteractionSource(),
                    placeholder = {
                        if(reportContent.value=="")Text("내용을 입력해주세요.")
                    }
                ) {

                }
            }
        }
        Box(modifier= Modifier
            .fillMaxHeight()
            .fillMaxWidth()){
            TextButton(onClick = { bookViewModel.bookReportRegist(BookReportReq(searchResult!!.getValue("bookId").toInt(), reportTitle.value, reportContent.value))},
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



}