package com.example.frontend.ui.screens.info

import android.graphics.Paint.Align
import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.frontend.ui.components.Avatar
import com.example.frontend.ui.components.FuncBtn
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import com.example.frontend.R
import com.example.frontend.ui.components.PageBtn
import com.example.frontend.ui.components.ReviewCard
import com.example.frontend.ui.vo.Routes
import com.example.frontend.viewmodel.AuthViewModel
import com.example.frontend.viewmodel.UserViewModel

@Composable
fun MyPage(navController : NavHostController, isMine: Boolean = false, userId: String, authViewModel: AuthViewModel, userViewModel: UserViewModel){
     var name : String = ""
     var bookCnt : Int = 0
     var reviewCnt : Int = 0
     if(userId == "ssafy"){
          name = "김싸피"
          bookCnt = 3
          reviewCnt = 5
     } else if(userId == "ssafy2"){
          name = "박싸피"
          bookCnt = 5
          reviewCnt = 8
     }
     userViewModel.getInfo(authViewModel.accessToken.value)
     val userInfo: Map<String, String> = userViewModel.userInfo.value
     // userId 기반으로 이름, 도서 수, 리뷰 수 받아오기
     LazyColumn(modifier = Modifier.fillMaxHeight()){
          item{
               Row(
                    modifier = Modifier
                         .fillMaxWidth()
                         .padding(top = 40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
               ){
                    Avatar(80)
                    Text(
                         userInfo.get("nickname") + "님",
                         fontSize = 30.sp,
                         textAlign = TextAlign.Left,
                         modifier = Modifier.padding(20.dp)
                    )
               }
          }
          if(isMine){
               item{
                    Row(
                         modifier = Modifier.fillMaxWidth(),
                         horizontalArrangement = Arrangement.Center
                    ){
                         PageBtn(navController = navController, name = "프로필 수정", destination = Routes.MODIFYINFO)
                    }
               }
          }
          item{
               Text(
                    "유저평점",
                    fontSize = 25.sp,
                    modifier = Modifier.padding(30.dp),
                    fontWeight = FontWeight.Bold
               )
               Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
               ){
                    for (i : Int in 1..3){
                         Image(
                              painter = painterResource(id = R.drawable.ic_star_yellow),
                              contentDescription = null,
                              contentScale = ContentScale.Fit,
                              modifier = Modifier.size(30.dp)
                         )
                    }
                    for (i : Int in 4..5) {
                         Image(
                              painter = painterResource(id = R.drawable.ic_star),
                              contentDescription = null,
                              contentScale = ContentScale.Fit,
                              modifier = Modifier.size(30.dp)
                         )
                    }
               }
          }
          item{
               Text(
                    "도서 관리",
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 30.dp, top = 30.dp, end = 30.dp, bottom = 10.dp),
                    fontWeight = FontWeight.Bold
               )
               Row(
                    modifier = Modifier
                         .fillMaxWidth()
                         .padding(horizontal = 40.dp, vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
               ){
                    Text(
                         "보유 중인 도서",
                         fontSize = 20.sp,
                         modifier = Modifier.padding(start = 10.dp)
                         )
                    Row(
                         verticalAlignment = Alignment.CenterVertically
                    ){
                         Text(bookCnt.toString(), fontSize = 20.sp)
                         Image(
                              painter = painterResource(id = R.drawable.next),
                              contentDescription = "",
                              contentScale = ContentScale.Fit,
                              modifier = Modifier
                                   .size(15.dp)
                                   .clickable { navController.navigate(Routes.BOOKLIST) },
                         )
                    }
               }
          }
          item{
               Text(
                    "평가",
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 30.dp, top = 30.dp, end = 30.dp, bottom = 30.dp),
                    fontWeight = FontWeight.Bold
               )
               Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
               ){
                    Column(
                    ){
                         for(i: Int in 1..reviewCnt){
                              ReviewCard()
                         }
                    }
               }
          }
     }

}