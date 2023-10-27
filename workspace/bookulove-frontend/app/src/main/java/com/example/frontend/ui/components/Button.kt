package com.example.frontend.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun PageBtn(navController: NavHostController, name: String, destination: String){
    Button(
        onClick = {navController.navigate(destination)},
        shape = RoundedCornerShape(5.dp),
    ){
        Text(text = name)
    }
}

@Composable
fun FuncBtn(name: String, onClick: () -> Unit){
    Button(
        onClick = {onClick()},
        shape = RoundedCornerShape(5.dp),
    ){
        Text(text = name)
    }
}