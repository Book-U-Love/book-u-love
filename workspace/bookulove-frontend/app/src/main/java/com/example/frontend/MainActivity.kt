@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.frontend

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.frontend.data.api.API
import com.example.frontend.data.api.UserApi
import com.example.frontend.ui.components.BookReportDetail
import com.example.frontend.ui.components.ReportDetailViewModel
import com.example.frontend.ui.screens.book.BookList
import com.example.frontend.ui.screens.book.BookSearch
import com.example.frontend.ui.screens.book.BookTotal
import com.example.frontend.ui.screens.book.BookTransactionRegist
import com.example.frontend.ui.screens.main.Home
import com.example.frontend.ui.screens.info.MyPage
import com.example.frontend.ui.screens.user.Chat
import com.example.frontend.ui.screens.user.ChatRoom
import com.example.frontend.ui.screens.user.Register
import com.example.frontend.ui.theme.FrontEndTheme
import com.example.frontend.ui.vo.Routes
import com.example.frontend.ui.vo.bookList
import com.example.frontend.viewmodel.AuthViewModel
import com.example.frontend.viewmodel.AuthViewModelFactory
import com.example.frontend.viewmodel.MainViewModel
import com.example.frontend.viewmodel.MainViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

@SuppressLint("MissingPermission")
class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val curLocation = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
                permissions ->
            when{
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {

                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {

                }
                else -> {
                }
            }
        }
        curLocation.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION)
        )
        setContent {
            val mainViewModel = ViewModelProvider(
                this,MainViewModelFactory()
            )[MainViewModel::class.java]
            FrontEndTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainApp(mainViewModel)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun MainApp(viewModel: MainViewModel){
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
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.back), contentDescription ="goBack")
                    }
                },
                title = {
                    Text(text = viewModel.navState.value)
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
            bottomBar ={
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
                verticalArrangement = Arrangement.spacedBy(1.dp),
            ) {
                Divider()
                MainNavigation(navController = navController,viewModel)
            }

        }
    }
}
@Composable
fun MainNavigation(navController: NavHostController, viewModel:MainViewModel){
    NavHost(navController = navController, startDestination = Routes.BOOKTRANSACTIONREGIST) {
        composable(route = Routes.HOME) {
            Home(navController = navController)
            viewModel.changeState("홈")
        }
        composable(route = Routes.CHAT) {
            Chat(navController)
            Log.d("check",navController.graph.id.toString())
            viewModel.changeState("채팅")
            Log.d("stack", navController.toString())
        }
        composable(route = Routes.MYPAGE) {
            MyPage(navController,true,"asdf")
            viewModel.changeState("마이페이지")
            Log.d("stack", navController.toString())
        }
        composable(route = Routes.MYPAGE + "/{userId}",
                arguments = listOf(navArgument("userId"){
                    type = NavType.StringType
                })
            ) { entry ->
            val userId = entry.arguments?.getString("userId")
            if (userId != null) {
                MyPage(
                    navController,
                    userId == "ssafy",
                    userId
                )
            } else {
                Home(navController = navController)
            }
        }
        composable(route = Routes.BOOKSEARCH) {
            BookSearch(navController)
            viewModel.changeState("검색")
            Log.d("stack", navController.toString())
        }
        composable(route = Routes.BOOKTOTAL) {
            BookTotal(navController)
            viewModel.changeState("독후감")
            Log.d("stack", navController.toString())
        }
        composable(route = Routes.CHATROOM) {
            ChatRoom()
            viewModel.changeState("김싸피")
        }
        composable(route = Routes.REGISTER) {
            Register(navController = navController)
            viewModel.changeState("회원가입")
        }
        composable(route = Routes.REPORTDETAIL+"/{index}",
            arguments = listOf(navArgument("index"){
                type = NavType.IntType
            })) {
            entry ->
            val reportIndex = entry.arguments?.getInt("index");
           if(reportIndex!=null) {
               BookReportDetail(reportIndex)

           }
        }
        composable(route = Routes.BOOKLIST){
            BookList()
        }
        composable(route = Routes.BOOKTRANSACTIONREGIST){
            BookTransactionRegist()
        }
    }

}