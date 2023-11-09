package com.example.frontend.ui.screens.user

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.frontend.ui.components.FuncBtn
import com.example.frontend.ui.components.InputField
import com.example.frontend.ui.components.MapInfo
import com.example.frontend.ui.components.PageBtn
import com.example.frontend.ui.vo.Routes
import com.google.android.gms.maps.model.LatLng


@ExperimentalMaterial3Api
@Composable
fun Modify(navController: NavHostController){
    var isFirst by remember {
        mutableStateOf(true)
    }
    if(isFirst){
        FirstModify(navController, changePage = {isFirst = false})
    } else {
        SecondModify(navController, changePage = { isFirst = true })
    }
}

@ExperimentalMaterial3Api
@Composable
fun FirstModify(navController: NavHostController, changePage: () -> Unit){
    var id by remember {
        mutableStateOf("ssafy123")
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
        mutableStateOf("김싸피")
    }
    var libName : String by remember{ mutableStateOf("") }
    var libDetail : String by remember{ mutableStateOf("") }
    val state = rememberLazyListState()
    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = state,
            ){
            item{
                InputField(value = id, label = "아이디", onValueChanged = {id = it})
                val openDialog = remember{ mutableStateOf(false) }

                FuncBtn(name = "비밀번호 수정", onClick = {openDialog.value = !openDialog.value})
                if(openDialog.value){
                    PwModify(
                        onDismissRequest = { openDialog.value = false },
                        onConfirmation = { 
                            openDialog.value = false
                            //패스워드 변경 함수 진행
                         },
                        pw = pw,
                        confirmPw = confirmPw,
                        onPwChanged = {pw = it},
                        onConfirmPwChanged = {confirmPw = it}
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                InputField(value = phNum, label = "전화번호", onValueChanged = {phNum = it})
                FuncBtn(
                    name = "인증번호 받기",
                    onClick = { /*TODO*/ }
                )
                Spacer(modifier = Modifier.height(30.dp))
                InputField(value = confirmNum, label = "인증번호", onValueChanged = {confirmNum = it})
                InputField(value = nickname, label = "닉네임", onValueChanged = {nickname = it})
                InputField(value = libName, label = "도서관명", onValueChanged = {libName = it})
                InputField(value = libDetail, label = "상세설명", onValueChanged = {libDetail = it})
            }
            item{
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    PageBtn(
                        navController = navController,
                        "돌아가기",
                        Routes.MYPAGE
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
}

@Composable
fun SecondModify(navController: NavHostController, changePage: () -> Unit){
    val pos = remember { mutableStateOf(LatLng(0.0, 0.0)) }
    Row(
        modifier = Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            MapInfo(isModify = true, pos = pos, title = "modify", detail = "modify")
            Spacer(modifier = Modifier.height(50.dp))
            Row() {
                FuncBtn(
                    onClick = {changePage()},
                    name = "이전으로"
                )
                Spacer(modifier = Modifier.width(80.dp))
                PageBtn(
                    navController = navController,
                    name = "수정하기",
                    Routes.MYPAGE
                )
            }

        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun PwModify(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    pw: String,
    confirmPw: String,
    onPwChanged: (String) -> Unit,
    onConfirmPwChanged: (String) -> Unit
){
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card (
            modifier = Modifier.fillMaxWidth(),
        ){
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                InputField(value = pw, label = "비밀번호", isPassword = true, onValueChanged = onPwChanged)
                InputField(value = confirmPw, label = "비밀번호 확인", isPassword = true, needSpacer = false, onValueChanged = onConfirmPwChanged)
                if(pw == "" || confirmPw == ""){
                    Spacer(modifier = Modifier.height(30.dp))
                } else if (pw == confirmPw){
                    Text(text = "비밀번호가 일치합니다", color = MaterialTheme.colorScheme.secondary, modifier = Modifier.height(30.dp))
                } else{
                    Text(text = "비밀번호가 일치하지 않습니다", color = MaterialTheme.colorScheme.error, modifier = Modifier.height(30.dp))
                }
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround){
                    FuncBtn(name = "취소", onClick = {onDismissRequest()})
                    FuncBtn(name = "수정", onClick = {
                        if(pw == confirmPw){
                            onConfirmation()
                        }
                    })
                }
            }
        }
    }
}