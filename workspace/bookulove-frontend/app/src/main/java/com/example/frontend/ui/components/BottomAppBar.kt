package com.example.frontend.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.frontend.R
import com.example.frontend.ui.vo.Routes

@Composable
fun BottomAppBar(navController: NavHostController){

        Row(
            modifier = Modifier
                .padding(25.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                navPopUpTo(navController, Routes.HOME) }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_home_24),
                    contentDescription = "home"
                )
            }
            IconButton(onClick = { navPopUpTo(navController, Routes.CHAT) }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_chat_bubble_outline_24),
                    contentDescription = "chat"
                )
            }
            IconButton(onClick = { navPopUpTo(navController, Routes.BOOKTOTAL)}){
                Icon(painter = painterResource(id=R.drawable.baseline_menu_book_24),
                    contentDescription = "bookTotal"
                )
            }
            IconButton(onClick = { navPopUpTo(navController, Routes.BOOKSEARCH)}) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "search"
                )
            }
            IconButton(onClick = { navPopUpTo(navController, Routes.MYPAGE) })
            {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = "settings"
                )
            }
        }
}
fun navPopUpTo(navController: NavController, route:String){
    navController.navigate(route){
        popUpTo(navController.graph.id){
            inclusive = true

        }
    }
}
