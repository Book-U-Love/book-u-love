package com.example.frontend.ui.screens.user

import android.Manifest
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.frontend.ui.components.FuncBtn
import com.example.frontend.ui.components.PageBtn
import com.example.frontend.ui.components.InputField
import com.example.frontend.ui.components.MapInfo
import com.example.frontend.ui.theme.FrontEndTheme
import com.example.frontend.ui.vo.Routes


@Composable
fun Register(navController: NavHostController){
    var isFirst by remember {
        mutableStateOf(true)
    }
    if(isFirst){
        FirstRegister(navController, changePage = {isFirst = false})
    } else{
        SecondRegister(navController, changePage = {isFirst = true})
    }
}

@Composable
fun FirstRegister(navController: NavHostController, changePage: () -> Unit){
    var id by remember {
        mutableStateOf("")
    }
    var pw by remember {
        mutableStateOf("")
    }
    var confirmPw by remember {
        mutableStateOf("")
    }
    var phNum by remember {
        mutableStateOf("")
    }
    var confirmNum by remember {
        mutableStateOf("")
    }
    var nickname by remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            InputField(value = id, label = "아이디", onValueChanged = {id = it})
            InputField(value = pw, label = "비밀번호", isPassword = true, onValueChanged = {pw = it})
            InputField(value = confirmPw, label = "비밀번호 확인", isPassword = true, needSpacer = false, onValueChanged = {confirmPw = it})
            if(pw == "" || confirmPw == ""){
                Spacer(modifier = Modifier.height(30.dp))
            } else if (pw == confirmPw){
                Text(text = "비밀번호가 일치합니다", color = MaterialTheme.colorScheme.secondary, modifier = Modifier.height(30.dp))
            } else{
                Text(text = "비밀번호가 일치하지 않습니다", color = MaterialTheme.colorScheme.error, modifier = Modifier.height(30.dp))
            }
            InputField(value = phNum, label = "전화번호", onValueChanged = {phNum = it})
            FuncBtn(
                name = "인증번호 받기",
                onClick = { /*TODO*/ }
            )
            Spacer(modifier = Modifier.height(30.dp))
            InputField(value = confirmNum, label = "인증번호", onValueChanged = {confirmNum = it})
            InputField(value = nickname, label = "닉네임", onValueChanged = {nickname = it})
            Row(){
                PageBtn(
                    navController = navController,
                    "돌아가기",
                    Routes.HOME
                )
                Spacer(modifier = Modifier.width(80.dp))
                FuncBtn(
                    onClick = {changePage()},
                    name = "다음으로"
                )
            }
        }
    }
}

@Composable
fun SecondRegister(navController: NavHostController, changePage: () -> Unit){
    Row(
        modifier = Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            MapInfo()
            Spacer(modifier = Modifier.height(50.dp))
            PageBtn(
                navController = navController,
                name = "회원가입",
                Routes.HOME
            )

        }
    }
}