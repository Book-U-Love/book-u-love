package com.example.frontend.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
@Composable
fun ToggleBtn(
    onClick: () -> Unit,
    state: Boolean,
    modifier:Modifier=Modifier,
    text:String,
){
    Button(onClick = onClick,
        contentPadding = PaddingValues(top=15.dp, bottom=15.dp, start=30.dp, end=30.dp),
        colors = ButtonDefaults.buttonColors(
            when(state){
                true->Color.Black;
                false ->Color.White;
            },
            when(state){
                true->Color.White;
                false->Color.Black;
            }
        ),
        modifier = Modifier.border(1.dp, Color.Black, RoundedCornerShape(30.dp))){
        Text(text)
    }
}