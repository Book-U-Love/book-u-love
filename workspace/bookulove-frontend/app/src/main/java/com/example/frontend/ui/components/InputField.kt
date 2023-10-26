package com.example.frontend.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

/*
    입력창 컴포넌트
    value : 입력되는 값이 무엇인지
    label : 입력창 설명
    isPassword : 비밀번호 여부 확인(비밀번호는 입력값 가림)
    onValueChanged : 값 변경시 value의 변수에 적용

    사용법
    var 입력받는변수 by remember {
        mutableStateOf("")
    }
    InputField(변수, "설명란", 비밀번호여부, onValueChanged = {변수 = it})
 */
@Composable
fun InputField(value: String, label: String, isPassword: Boolean, onValueChanged: (String) -> Unit){
    Row(){
        TextField(
            value = value,
            label = { Text(text = label)},
            onValueChange = {onValueChanged(it)},
            visualTransformation = if(isPassword) PasswordVisualTransformation() else VisualTransformation.None
        )
    }
    Spacer(modifier = Modifier.height(30.dp))
}