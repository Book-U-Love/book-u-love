package com.example.frontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DropDown(
    onClick:()->Unit,
    modifier: Modifier=Modifier,
    interactionSource: MutableInteractionSource = remember{ MutableInteractionSource() },
    content: @Composable RowScope.()-> Unit
){
    var dropDownState = remember {
        mutableStateOf(false)
    }

    Button(modifier=Modifier.background(Color.White)
        ,onClick = {dropDownState!=dropDownState}){
        Text("asdf")
    }
}