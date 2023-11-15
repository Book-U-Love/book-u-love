package com.example.frontend.ui.screens.book

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.frontend.data.model.BookRegistReq
import com.example.frontend.data.model.BookReportReq
import com.example.frontend.ui.components.CustomDialog
import com.example.frontend.ui.components.DropDown
import com.example.frontend.ui.components.ToggleBtn
import com.example.frontend.ui.theme.SkyBlue
import com.example.frontend.ui.vo.Routes
import com.example.frontend.viewmodel.BookViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookTransactionRegist(bookViewModel: BookViewModel, navController:NavController){

    val transactionState = remember{
        mutableStateOf(0)
    }
    var titleValue = remember{
        mutableStateOf("")
    }
    var descValue = remember{
        mutableStateOf("")
    }
    var conditionValue = remember{
        mutableStateOf("")
    }
    var scrollState = rememberScrollState();
    val bookSearchRes = bookViewModel.bookSearchRes.collectAsState()
    val bookCondition = mapOf<String, Number>("매우 좋음" to 5,"좋음" to 4, "보통" to 3, "나쁨" to 2, "매우 나쁨" to 1)
    val checkState = remember {
        mutableStateOf(false)
    }

    Box {
        Column(modifier= Modifier
            .fillMaxSize()
            .padding(15.dp)
            .verticalScroll(scrollState)){
            Box(){
                Column {
                    Text("선택한 책", fontWeight = FontWeight.Bold, fontSize = 21.sp)
                    OutlinedTextField(modifier= Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth(),
                        value = bookSearchRes.value!!.getValue("title") ,
                        onValueChange = {},
                        readOnly = true)
                }
            }
            Box(modifier = Modifier.padding(top=15.dp)){
                Column {
                    Text("제목", fontWeight = FontWeight.Bold, fontSize = 21.sp)
                    OutlinedTextField(
                        value = titleValue.value,
                        onValueChange ={titleValue.value=it},
                        maxLines=5,
                        modifier= Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth(),
                        placeholder = {Text("제목")})
                }
            }
            Box(modifier = Modifier.padding(top=15.dp)){
                Column {
                    Text("상태", fontWeight = FontWeight.Bold, fontSize = 21.sp, modifier=Modifier.padding(bottom=10.dp))
                    DropDown(itemList = listOf("매우 좋음","좋음","보통","나쁨","매우 나쁨"), conditionValue.value){
                        conditionValue.value = it
                        Log.d("cond",conditionValue.value)
                    }
                }
            }
            Box(modifier=Modifier.padding(top=15.dp)){
                Column {
                    Text("거래여부", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Row(modifier = Modifier.padding(top = 10.dp)) {
                        ToggleBtn(onClick = {
                            transactionState.value = 0
                        }, state = transactionState.value==0, text = "거래안함")
                        ToggleBtn(onClick = {
                            transactionState.value = 1
                        }, state = transactionState.value==1, text = "판매하기")
                        ToggleBtn(onClick = {
                            transactionState.value = 2
                        }, state = transactionState.value==2, text = "대여하기")
                    }
                }
            }

            Box(modifier = Modifier.padding(top=15.dp)){
                Column {
                    Text("자세한 설명", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    OutlinedTextField(value = descValue.value,
                        onValueChange ={descValue.value=it},
                        maxLines=5,
                        modifier= Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f)
                            .animateContentSize(),
                        placeholder = {Text("책과 관련된 사항을 모두 작성해주세요. ")})
                }

            }

            Box(modifier= Modifier
                .fillMaxHeight()
                .fillMaxWidth()){
                TextButton(onClick = {
                    bookViewModel.bookRegist(
                        BookRegistReq(
                            bookSearchRes.value!!.getValue("isbn").toString(),
                                        bookCondition.getValue(conditionValue.value),
                                        if(transactionState.value==1) true else false,
                                        if(transactionState.value==2) true else false,
                                        descValue.value))
                    checkState.value= true },
                    modifier= Modifier
                        .padding(top = 15.dp)
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(SkyBlue, RoundedCornerShape(15.dp))
                ) {
                    Text(text = "도서 등록", color = Color.White
                        , fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        }
    }

    Box(){
        if(checkState.value){
            if(conditionValue.value!=""){
                CustomDialog(
                    onConfirmation = { navController.navigate(Routes.MYLIBRARY) },
                    dialogTitle = "도서 등록 완료!",
                    dialogText = "등록이 완료되었습니다.",
                    dialogColor = Color.Blue.copy(alpha = 0.5f),
                )
            }else{
                CustomDialog(
                    onConfirmation = { checkState.value=false },
                    dialogTitle = "도서 등록 실패",
                    dialogText = "등록에 실패했습니다.",
                    dialogColor = Color.Red.copy(alpha = 0.5f),
                )
            }

        }
    }
}
