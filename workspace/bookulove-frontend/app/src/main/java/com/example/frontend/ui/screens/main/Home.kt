package com.example.frontend.ui.screens.main

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frontend.R
import com.example.frontend.ui.components.FuncBtn
import com.example.frontend.ui.components.InputField
import com.example.frontend.ui.components.PageBtn
import com.example.frontend.ui.screens.book.BookSearch
import com.example.frontend.ui.screens.book.BookTotal
import com.example.frontend.ui.screens.info.MyPage
import com.example.frontend.ui.screens.user.Chat
import com.example.frontend.ui.screens.user.Register
import com.example.frontend.ui.vo.Routes
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

@Composable
fun Home(navController: NavHostController) {
    var isLogin by remember {
        mutableStateOf(false)
    }
    if(!isLogin){
        BeforeLogin(navController = navController, changePage = {isLogin = true})
    } else{
        AfterLogin(navController = navController)
    }
}


@Composable
fun BeforeLogin(navController: NavHostController, changePage: () -> Unit){
    Row(
        modifier = Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.mark),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(250.dp, 250.dp)
                )
            }
            var id by remember {
                mutableStateOf("")
            }
            InputField(id, "id", onValueChanged = { id = it })
            var pw by remember {
                mutableStateOf("")
            }
            InputField(pw, "password", true, onValueChanged = { pw = it })
            Row() {
                PageBtn(
                    navController = navController,
                    name = "회원가입",
                    destination = Routes.REGISTER
                )
                Spacer(modifier = Modifier.width(80.dp))
                FuncBtn(
                    name = "로그인",
                    onClick = {changePage()}
                )
            }
        }
    }
}

@Composable
fun AfterLogin(navController: NavHostController){
}