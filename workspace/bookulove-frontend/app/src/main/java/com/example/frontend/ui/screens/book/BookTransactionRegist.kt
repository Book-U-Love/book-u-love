package com.example.frontend.ui.screens.book

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontend.ui.components.DropDown
import com.example.frontend.ui.components.ToggleBtn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookTransactionRegist(){
    val transactionState = remember{
        mutableStateOf(true)
    }
    var descValue = remember{
        mutableStateOf("")
    }
    var scrollState = rememberScrollState();
    Surface(modifier=Modifier.padding(15.dp)){
        Column {
            Box(){
                Column {
                    Text("책 선택하기", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    DropDown(onClick = { /*TODO*/ }) {
                    }
                }
            }
            Box(modifier=Modifier.padding(top=15.dp)){
                Column {
                    Text("거래방식", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Row(modifier = Modifier.padding(top = 15.dp)) {
                        ToggleBtn(onClick = {
                            if (!transactionState.value) transactionState.value =
                                !transactionState.value
                        }, state = transactionState.value, text = "판매하기")
                        ToggleBtn(onClick = {
                            if (transactionState.value) transactionState.value =
                                !transactionState.value
                        }, state = !transactionState.value, text = "대여하기")
                    }
                }
            }

            Box(modifier = Modifier.padding(top=15.dp)){
                Column {
                    Text("자세한 설명", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    OutlinedTextField(value = descValue.value,
                        onValueChange ={descValue.value=it},
                        maxLines=5,
                        modifier=Modifier.fillMaxWidth().verticalScroll(scrollState),
                        placeholder = {Text("책과 관련된 사항을 모두 작성해주세요. ")})
                }

            }

        }
    }
}
