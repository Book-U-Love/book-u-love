package com.example.frontend.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.frontend.R


@Composable
fun Avatar(size:Int){
    Image(modifier= Modifier.padding(15.dp).size(size.dp),painter = painterResource(id = R.drawable.user_default), contentDescription = "user_default")
}