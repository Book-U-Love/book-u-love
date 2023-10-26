@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.frontend

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frontend.ui.screens.book.BookSearch
import com.example.frontend.ui.screens.book.BookTotal
import com.example.frontend.ui.screens.main.Home
import com.example.frontend.ui.screens.info.MyPage
import com.example.frontend.ui.screens.user.Chat
import com.example.frontend.ui.screens.user.ChatRoom

import com.example.frontend.ui.theme.FrontEndTheme
import com.example.frontend.ui.vo.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontEndTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainApp()
                }
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun MainApp(){
    val navController = rememberNavController()
    Log.d("asdf", navController.toString())
//    val pagerState = rememberPagerState(pageCount=2)
    Surface {
        Scaffold(topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.back), contentDescription ="goBack")
                    }
                },
                title = {
                    Text(text = "마이페이지")
                },
                actions = {
                    // RowScope here, so these icons will be placed horizontally

                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            )
        },
            bottomBar = {
                com.example.frontend.ui.components.BottomAppBar(navController = navController)
            },
//            floatingActionButton = {
//                FloatingActionButton(onClick = {  }) {
//                    Icon(Icons.Default.Add, contentDescription = "Add")
//                }
//            }
        ){  innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                MainNavigation(navController = navController)
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
        composable(route = Routes.CHAT){
            Chat(navController)
        }
        composable(route = Routes.MYPAGE){
            MyPage()
        }
        composable(route = Routes.BOOKSEARCH){
            BookSearch()
        }
        composable(route = Routes.BOOKTOTAL){
            BookTotal()
        }
        composable(route = Routes.CHATROOM){
            ChatRoom()
        }
    }

}