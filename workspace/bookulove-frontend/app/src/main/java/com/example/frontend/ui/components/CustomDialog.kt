package com.example.frontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontend.ui.theme.Red


@Composable
fun CustomDialog(
    onDismissRequest:() ->Unit,
    onConfirmation:() ->Unit,
    dialogTitle: String,
    dialogText: String,
){
    Surface(color=Color.Transparent){
        Column(modifier= Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Box(){
                Column(modifier=Modifier.fillMaxWidth(0.9f).background(Color.White,
                    RoundedCornerShape(15.dp)).padding(25.dp))
                {
                    Box(modifier=Modifier.fillMaxWidth()){
                        Text("삭제하시겠습니까?",
                            fontSize = 24.sp,modifier=Modifier.align(Alignment.Center)
                        ,   fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.padding(top=30.dp))
                    Box{
                        Row(modifier=Modifier.fillMaxWidth()
                        , horizontalArrangement = Arrangement.Center){
                            TextButton(
                                onClick = {onConfirmation()},
                                modifier=Modifier.background(Red,shape= RoundedCornerShape(15.dp))
                                ) {
                                Text("삭제", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp,
                                    modifier=Modifier.padding(start=40.dp, end=40.dp, top=10.dp, bottom=10.dp))
                            }
                            Spacer(modifier = Modifier.padding(end=15.dp))
                            TextButton(
                                onClick = { onDismissRequest()},
                                modifier=Modifier.border(1.dp, Red, RoundedCornerShape(15.dp))
                                    ) {
                                Text("취소",color = Red, fontWeight = FontWeight.Bold, fontSize = 18.sp,modifier=Modifier.padding(start=40.dp, end=40.dp, top=10.dp, bottom=10.dp))
                            }
                        }
                    }
                }
            }
        }
    }


}