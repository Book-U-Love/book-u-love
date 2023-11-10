package com.example.frontend.ui.screens.user


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.frontend.data.model.Certification
import com.example.frontend.data.model.PhoneNumber
import com.example.frontend.data.model.UserRegistDto
import com.example.frontend.ui.components.FuncBtn
import com.example.frontend.ui.components.PageBtn
import com.example.frontend.ui.components.InputField
import com.example.frontend.ui.components.MapInfo
import com.example.frontend.ui.components.Message
import com.example.frontend.ui.components.NumberField
import com.example.frontend.ui.vo.Routes
import com.example.frontend.viewmodel.AuthViewModel
import com.example.frontend.viewmodel.UserViewModel
import com.google.android.gms.maps.model.LatLng

private val userDto: UserRegistDto = UserRegistDto("", "", "", "", "",0.0, 0.0)

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

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FirstRegister(navController: NavHostController, changePage: () -> Unit){
    val authViewModel: AuthViewModel = AuthViewModel()
    val userViewModel:UserViewModel = UserViewModel()
    var id by remember { mutableStateOf("")}
    var pw by remember { mutableStateOf("")}
    var confirmPw by remember { mutableStateOf("")}
    var phNum by remember { mutableStateOf("")}
    var confirmNum by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var libName : String by remember{ mutableStateOf("") }
    val pwCheck = remember{ mutableStateOf(false) }
    val certRes by authViewModel.certSendRes.collectAsState()
    val certCheck = remember{ mutableStateOf(false) }
    val certChkRes by authViewModel.certChkRes.collectAsState()
    val errChk = remember{ mutableStateOf(false) }
    val errMsg = remember{ mutableStateOf("") }
    val sucChk = remember{ mutableStateOf(false) }
    val sucMsg = remember{ mutableStateOf("") }
    val sucFun = remember{ mutableStateOf({}) }
    val regChk by userViewModel.signupRes.collectAsState()
    Row(
        modifier = Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ){
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item{
                Spacer(modifier = Modifier.height(30.dp))
                FuncBtn(name = "위치 등록", onClick = { changePage() })
                Spacer(modifier = Modifier.height(30.dp))
                InputField(value = id, label = "아이디", onValueChanged = {id = it})
                InputField(value = pw, label = "비밀번호", isPassword = true, onValueChanged = {pw = it})
                InputField(value = confirmPw, label = "비밀번호 확인", isPassword = true, needSpacer = false, onValueChanged = {confirmPw = it})
                if(pw == "" || confirmPw == ""){
                    pwCheck.value = false
                    Spacer(modifier = Modifier.height(30.dp))
                } else if (pw == confirmPw){
                    pwCheck.value = true
                    Text(text = "비밀번호가 일치합니다", color = MaterialTheme.colorScheme.secondary, modifier = Modifier.height(30.dp))
                } else{
                    pwCheck.value = false
                    Text(text = "비밀번호가 일치하지 않습니다", color = MaterialTheme.colorScheme.error, modifier = Modifier.height(30.dp))
                }
                NumberField(value = phNum, label = "전화번호", onValueChanged = {phNum = it}, enable = !certCheck.value)
                FuncBtn(
                    name = "인증번호 받기",
                    onClick = { authViewModel.sendCertification(PhoneNumber(phNum)) },
//                    onClick = {},
                    enable = !certCheck.value
                )

                if(certRes == "success"){
                    Text(text = "인증번호가 전송되었습니다.", color = Color.Green, modifier = Modifier.height(30.dp))
                } else{
                    Spacer(modifier = Modifier.height(30.dp))
                }
                NumberField(value = confirmNum, label = "인증번호", onValueChanged = {confirmNum = it}, enable = !certCheck.value)
                FuncBtn(
                    name = "인증",
                    onClick = { authViewModel.checkCertification(Certification(phNum, confirmNum))},
//                    onClick = {certCheck.value = true},
                    enable = !certCheck.value
                )
                if(certChkRes == "success"){
                    certCheck.value = true
                    Text(text = "인증이 성공하였습니다", color = Color.Green, modifier = Modifier.height(30.dp))
                } else{
                    Spacer(modifier = Modifier.height(30.dp))
                }
                InputField(value = nickname, label = "닉네임", onValueChanged = {nickname = it})
                InputField(value = libName, label = "도서관명", onValueChanged = {libName = it})

                Row(){
                    PageBtn(
                        navController = navController,
                        "돌아가기",
                        Routes.HOME,
                    )
                    Spacer(modifier = Modifier.width(80.dp))
                    FuncBtn(name = "회원가입", onClick = {
                        userDto.id = id
                        userDto.password = pw
                        userDto.phoneNumber = phNum
                        userDto.nickname = nickname
                        userDto.libraryName = libName
                        userViewModel.signUp(userDto)
                    })
                }

            }
            if(regChk == "success"){
                sucChk.value = true
                sucMsg.value = "회원가입에 성공했습니다"
                sucFun.value = {navController.navigate(Routes.HOME)}
            } else if(regChk == "fail"){
                errChk.value = true
                errMsg.value = "회원가입에 실패했습니다"
            }
        }
        if(errChk.value){
            Message(title = "Error", dialogClose = { errChk.value = false }, content = errMsg.value)
        }
        if(sucChk.value){
            Message(title = "Success", dialogClose = {sucChk.value = false}, content = sucMsg.value, confirmButton = sucFun.value)
        }
    }
}

@Composable
fun SecondRegister(navController: NavHostController, changePage: () -> Unit){
    val userViewModel:UserViewModel = UserViewModel()
    val res:String by userViewModel.signupRes.collectAsState()
    Row(
        modifier = Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ){
        val pos = remember { mutableStateOf(LatLng(0.0, 0.0)) }
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){

            MapInfo(pos= pos, title = "regist", detail = "regist")
            Spacer(modifier = Modifier.height(50.dp))
            Row() {
                FuncBtn(
                    onClick = {
                        userDto.lat = pos.value.latitude
                        userDto.lng = pos.value.longitude
                        changePage()
                      }
                    ,name="확인"
                    )
                }
            }

        }

    }
