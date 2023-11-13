package com.example.frontend.ui.screens.user

import android.graphics.Paint.Align
import android.util.Log
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
import androidx.compose.runtime.collectAsState
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
import com.example.frontend.data.model.ModifyPw
import com.example.frontend.data.model.ModifyUser
import com.example.frontend.ui.components.FuncBtn
import com.example.frontend.ui.components.InputField
import com.example.frontend.ui.components.MapInfo
import com.example.frontend.ui.components.Message
import com.example.frontend.ui.components.PageBtn
import com.example.frontend.ui.vo.Routes
import com.example.frontend.viewmodel.AuthViewModel
import com.example.frontend.viewmodel.UserViewModel
import com.google.android.gms.maps.model.LatLng

private val modifyDto: ModifyUser = ModifyUser("", "", 0.0, 0.0)

@ExperimentalMaterial3Api
@Composable
fun Modify(navController: NavHostController, authViewModel: AuthViewModel, userViewModel: UserViewModel){
    val userInfo: Map<String, String> = userViewModel.userInfo.value
    var isFirst by remember {
        mutableStateOf(true)
    }
    if(isFirst){
        FirstModify(navController, changePage = {isFirst = false}, authViewModel, userViewModel, userInfo)
    } else {
        SecondModify(navController, changePage = { isFirst = true }, userViewModel, userInfo)
    }
}

@ExperimentalMaterial3Api
@Composable
fun FirstModify(navController: NavHostController, changePage: () -> Unit, authViewModel: AuthViewModel, userViewModel: UserViewModel,
                userInfo: Map<String, String>){
    Log.d("find", userInfo.toString())

    var nickname by remember {
        mutableStateOf(userInfo.get("nickname").toString())
    }
    var libName by remember{ mutableStateOf(userInfo.get("libraryName").toString()) }
    val sucChk = remember{ mutableStateOf(false) }
    val sucMsg = remember{ mutableStateOf("") }
    val sucFun = remember{ mutableStateOf({}) }
    val modChk by userViewModel.modifyRes.collectAsState()
    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            ){
            item{
                Spacer(modifier = Modifier.height(30.dp))
                FuncBtn(name = "위치 등록", onClick = { changePage() })
                InputField(value = userInfo.get("loginId").toString(), label = "아이디", onValueChanged = {}, enable = false)
                val openDialog = remember{ mutableStateOf(false) }

                FuncBtn(name = "비밀번호 수정", onClick = {openDialog.value = !openDialog.value})
                if(openDialog.value){
                    PwModify(
                        onDismissRequest = { openDialog.value = false },
                        onConfirmation = { 
                            openDialog.value = false
                            //패스워드 변경 함수 진행
                         },
                        userViewModel,
                        authViewModel
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                InputField(value = nickname, label = "닉네임", onValueChanged = {nickname = it})
                InputField(value = libName, label = "도서관명", onValueChanged = {libName = it})
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
                        onClick = {
                            modifyDto.nickname = nickname
                            modifyDto.libraryName = libName
                            userViewModel.modifyUserInfo(authViewModel.accessToken.value, modifyDto)
                          },
                        name = "수정하기"
                    )
                }
                if(modChk == "success"){
                    sucChk.value = true
                    sucMsg.value = "회원정보 수정에 성공했습니다"
                    sucFun.value = {
                        userViewModel.resetState()
                        navController.navigate(Routes.MYPAGE)}
                }
                if(sucChk.value){
                    Message(title = "success", dialogClose = { sucChk.value = false }, confirmButton = sucFun.value, content = sucMsg.value)
                }
            }
        }
    }
}

@Composable
fun SecondModify(navController: NavHostController, changePage: () -> Unit, userViewModel: UserViewModel, userInfo: Map<String, String>){
    Row(
        modifier = Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ){
//        Log.d("find", userInfo.get("lat").toString() + " " +  userInfo.get("lng").toString())
        val pos = remember { mutableStateOf(LatLng(userInfo.get("lat").toString().toDouble(), userInfo.get("lng").toString().toDouble())) }
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){

            MapInfo(pos= pos, title = "regist", detail = "regist", isModify = true)
            Spacer(modifier = Modifier.height(50.dp))
            Row() {
                FuncBtn(
                    onClick = {
                        modifyDto.lat = pos.value.latitude
                        modifyDto.lng = pos.value.longitude
                        changePage()
                    }
                    ,name="확인"
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
    userViewModel: UserViewModel,
    authViewModel: AuthViewModel
){
    val modifyPw: ModifyPw = ModifyPw("", "")
    var oldPw by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var confirmPw by remember { mutableStateOf("") }
    val modPwRes by userViewModel.modiyfPwRes.collectAsState()
    val sucChk = remember{ mutableStateOf(false) }
    val sucMsg = remember{ mutableStateOf("") }
    val sucFun = remember{ mutableStateOf({}) }
    val errChk = remember{ mutableStateOf(false) }
    val errMsg = remember{ mutableStateOf("")}
    val errFun = remember{ mutableStateOf({})}
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card (
            modifier = Modifier.fillMaxWidth(),
        ){
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                InputField(value = oldPw, label = "기존 비밀번호", isPassword = true, onValueChanged = {oldPw = it})
                InputField(value = pw, label = "비밀번호", isPassword = true, onValueChanged = {pw = it})
                InputField(value = confirmPw, label = "비밀번호 확인", isPassword = true, needSpacer = false, onValueChanged = {confirmPw = it})
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
                            modifyPw.oldPassword = oldPw
                            modifyPw.newPassword = pw
                            userViewModel.modifyUserPw(authViewModel.accessToken.value, modifyPw)
                        }
                    })
                    if(modPwRes == "success"){
                        sucChk.value = true
                        sucMsg.value = "비밀번호 변경을 성공하였습니다"
                        sucFun.value = {
                            userViewModel.resetPwState()
                            sucChk.value = false
                            onConfirmation()
                        }
                    } else if(modPwRes == "fail"){
                        errChk.value = true
                        errMsg.value = "비밀번호 변경에 실패했습니다"
                        errFun.value = {
                            userViewModel.resetPwState()
                            errChk.value = false
                        }
                    }
                }
                if(sucChk.value){
                    Message(title = "Success", dialogClose = sucFun.value, content = sucMsg.value)
                }
                if(errChk.value){
                    Message(title = "Error", dialogClose = errFun.value, content = errMsg.value)
                }
            }
        }
    }
}