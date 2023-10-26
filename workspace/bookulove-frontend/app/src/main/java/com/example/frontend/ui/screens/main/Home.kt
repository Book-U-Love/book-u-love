package com.example.frontend.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.example.frontend.R
import com.example.frontend.ui.components.InputField

@Preview(showBackground = true)
@Composable
fun Home(){
    Row (
        modifier = Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(){
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
            InputField(id, "id", false, onValueChanged = {id = it})
            var pw by remember {
                mutableStateOf("")
            }
            InputField(pw, "password", true, onValueChanged = {pw = it})
        }
    }
}