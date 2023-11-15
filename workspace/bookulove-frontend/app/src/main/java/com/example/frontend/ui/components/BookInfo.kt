package com.example.frontend.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontend.R

@Composable
fun BookInfo(book: Map<String, String> = mapOf()){
    Surface {
        Row(modifier = Modifier
            .fillMaxWidth()){
            Box(modifier= Modifier
                .padding(15.dp)){
               Image(modifier=Modifier.size(100.dp),painter = painterResource(id = R.drawable.mark), contentDescription = "BookImage")
            }
            Box(){
                Column(modifier=Modifier.padding(start=15.dp,top=20.dp,end=15.dp, bottom = 10.dp)){
                    Box(){
                        Text(book.get("title").toString(), fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                    Box(modifier=Modifier.padding(top=3.dp, bottom=3.dp)){
                        Row {
                            Text("장덕동", fontSize = 12.sp, color = Color.Gray)
                            Text("5분 전", fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(start = 5.dp))
                            }
                    }
                    Row(){
                        if(book.get("allowSale") == "true"){
                            Box(modifier = Modifier.padding(1.dp)){
                                Box(modifier = Modifier
                                    .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(5.dp))
                                    .padding(3.dp)){
                                    Text("판매중", color=Color.White)
                                }
                            }
                        }
                        if(book.get("allowBorrow") == "true"){
                            Box(modifier = Modifier.padding(1.dp)){
                                Box(modifier = Modifier
                                    .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(5.dp))
                                    .padding(3.dp)){
                                    if(book.get("isBorrow").toString() == "true"){
                                        Text("대여중", color = Color.White)
                                    } else{
                                        Text("대여가능", color = Color.White)
                                    }
                                }
                            }
                        }
                    }
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        Box(modifier=Modifier.fillMaxWidth()){
                                Row {
                                    ElevatedButton(
                                        modifier= Modifier
                                            .width(80.dp)
                                            .height(40.dp),
                                        shape = RoundedCornerShape(5.dp),
                                        onClick = { /*TODO*/ }){
                                        Text("판매", )}
                                    ElevatedButton(
                                        modifier= Modifier
                                            .padding(start=10.dp)
                                            .width(80.dp)
                                            .height(40.dp),
                                        shape = RoundedCornerShape(5.dp),
                                        onClick = { /*TODO*/ }){
                                        Text("수정")}

                                }
                                }

                                }

                            }

                            }


                }

            }
        Divider(color=Color.Black.copy(alpha=0.3f))
}

