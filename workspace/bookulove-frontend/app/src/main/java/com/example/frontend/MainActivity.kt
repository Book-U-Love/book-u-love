@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.frontend.ui.screens.book.BookSearch
import com.example.frontend.ui.screens.main.Home
import com.example.frontend.ui.screens.info.MyPage

import com.example.frontend.ui.theme.FrontEndTheme
import com.example.frontend.ui.vo.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontEndTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    com.example.frontend.ui.components.Scaffold()
                }
            }
        }
    }
}

@Composable
fun MainNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Routes.HOME){
        composable(route = Routes.HOME){
            Home()
        }
        composable(route = Routes.MYPAGE){
            MyPage()
        }
        composable(route = Routes.BOOKSEARCH){
            BookSearch()
        }
    }

}