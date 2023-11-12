package com.example.frontend.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.frontend.R
import com.example.frontend.data.model.User
import com.example.frontend.ui.components.FuncBtn
import com.example.frontend.ui.components.InputField
import com.example.frontend.ui.components.MapInfo
import com.example.frontend.ui.components.Message
import com.example.frontend.ui.components.PageBtn
import com.example.frontend.ui.vo.Library
import com.example.frontend.ui.vo.Routes
import com.example.frontend.viewmodel.AuthViewModel
import com.example.frontend.viewmodel.MainViewModel
import com.google.android.gms.maps.model.LatLng

var userId: String = ""
@Composable
fun Home(navController: NavHostController, mainViewModel: MainViewModel, authViewModel: AuthViewModel) {
    var isLogin by remember {
        mutableStateOf(mainViewModel.isLogin.value)
    }
    if(!isLogin){
        BeforeLogin(navController = navController, changePage = {isLogin = true}, mainViewModel, authViewModel)
    } else{
        AfterLogin(navController = navController)
    }
}


@Composable
fun BeforeLogin(navController: NavHostController, changePage: () -> Unit, mainViewModel: MainViewModel, authViewModel: AuthViewModel){
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    val loginRes by authViewModel.loginRes.collectAsState()
    val errorFind = remember{ mutableStateOf(false) }
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
            InputField(id, "id", onValueChanged = { id = it })
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
                    onClick = {
                        val user:User = User(id, pw)
                        authViewModel.logIn(user)
                    }
                )
            }
            if(loginRes == "success"){
                mainViewModel.changeLoginState(true)
                changePage()
            } else if(loginRes == "fail"){
                errorFind.value = true
                authViewModel.resetState()
            }
            if(errorFind.value){
                Message(title = "Error", dialogClose = { errorFind.value = false }, content = "로그인 정보가 일치하지 않습니다.")
            }
        }
    }
}

@Composable
fun AfterLogin(navController: NavHostController){
    val pos = remember { mutableStateOf(LatLng(0.0, 0.0)) }
    var libList: List<Library> = listOf(
        Library("ssafy", LatLng(37.42267105057816,-122.08498723804952), "Test1", "Test1"),
        Library("ssafy1", LatLng(37.41267105057816,-122.08498723804952), "Test2", "Test1"),
        Library("ssafy2", LatLng(37.40267105057816,-122.08498723804952), "Test3", "Test1"),
        Library("ssafy3", LatLng(37.42267105057816,-122.07498723804952), "Test4", "Test1"),
        Library("ssafy4", LatLng(37.42267105057816,-122.05498723804952), "Test5", "Test1"),
    )
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        MapInfo(pos = pos, libList = libList, height = 800, navController = navController)
    }
}