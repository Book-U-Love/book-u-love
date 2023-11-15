package com.example.frontend.ui.screens.info

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.frontend.data.repository.PrefsRepository
import com.example.frontend.ui.components.ReviewCard
import com.example.frontend.viewmodel.AuthViewModel
import com.example.frontend.viewmodel.UserViewModel

@Composable
fun ReviewList(navHostController: NavHostController, userViewModel: UserViewModel, authViewModel: AuthViewModel){
    LaunchedEffect(key1 = Unit){
        userViewModel.getReviewList()
    }
    if(userViewModel.userReviewList.value.isNotEmpty()){
        LazyColumn(modifier = Modifier.fillMaxHeight()){
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
                        for(review in userViewModel.userReviewList.value){
                            ReviewCard(review.get("content").toString())
                        }
                    }
                }
            }
        }
    }
}