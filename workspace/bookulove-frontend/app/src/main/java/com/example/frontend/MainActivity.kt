@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.frontend

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
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
import androidx.compose.ui.platform.LocalFocusManager
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
import com.example.frontend.data.local.addFocusCleaner
import com.example.frontend.data.repository.PrefsRepository
import com.example.frontend.ui.components.BookReportDetail
import com.example.frontend.ui.components.BookReportRegist
import com.example.frontend.ui.screens.book.BookIsbnSearch
import com.example.frontend.ui.screens.book.BookList
import com.example.frontend.ui.screens.book.BookSearch
import com.example.frontend.ui.screens.book.BookTotal
import com.example.frontend.ui.screens.book.BookTransactionRegist
import com.example.frontend.ui.screens.main.Home
import com.example.frontend.ui.screens.info.MyPage
import com.example.frontend.ui.screens.info.ReviewList
import com.example.frontend.ui.screens.user.Chat
import com.example.frontend.ui.screens.user.ChatRoom
import com.example.frontend.ui.screens.user.Modify
import com.example.frontend.ui.screens.user.Register
import com.example.frontend.ui.theme.FrontEndTheme
import com.example.frontend.ui.vo.Routes
import com.example.frontend.viewmodel.AuthViewModel
import com.example.frontend.viewmodel.AuthViewModelFactory
import com.example.frontend.viewmodel.BookViewModel
import com.example.frontend.viewmodel.BookViewModelFactory
import com.example.frontend.viewmodel.MainViewModel
import com.example.frontend.viewmodel.MainViewModelFactory
import com.example.frontend.viewmodel.StompViewModel
import com.example.frontend.viewmodel.UserViewModel
import com.example.frontend.viewmodel.UserViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
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
            val userViewModel = ViewModelProvider(
                this,UserViewModelFactory()
            )[UserViewModel::class.java]
            val authViewModel = ViewModelProvider(
                this,AuthViewModelFactory()
            )[AuthViewModel::class.java]
            val bookViewModel = ViewModelProvider(
                this,BookViewModelFactory()
            )[BookViewModel::class.java]
            val stompViewModel = StompViewModel()
            FrontEndTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainApp(mainViewModel, userViewModel, authViewModel,bookViewModel, stompViewModel)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun MainApp(mainViewModel: MainViewModel, userViewModel: UserViewModel, authViewModel: AuthViewModel,bookViewModel: BookViewModel,stompViewModel:StompViewModel){
    val navController = rememberNavController()

    Surface(modifier=Modifier.addFocusCleaner(LocalFocusManager.current)){
        Scaffold(topBar = {
            if(mainViewModel.isLogin.value)
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
                    Text(text = mainViewModel.navState.value)
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
                if(mainViewModel.isLogin.value)
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
                MainNavigation(navController = navController,mainViewModel, userViewModel, authViewModel, bookViewModel,stompViewModel)
            }

        }
    }
}
@Composable
fun MainNavigation(navController: NavHostController, mainViewModel:MainViewModel, userViewModel: UserViewModel, authViewModel: AuthViewModel,bookViewModel: BookViewModel,stompViewModel: StompViewModel){
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(route = Routes.HOME) {
            Home(navController = navController, mainViewModel, userViewModel, authViewModel)
            mainViewModel.changeState("홈")
        }
        composable(route = Routes.CHAT) {
            Chat(navController,mainViewModel, stompViewModel)
            mainViewModel.changeState("채팅")
        }
        composable(route = Routes.MYPAGE) {
            MyPage(navController,true, authViewModel = authViewModel, userViewModel = userViewModel)
            mainViewModel.changeState("마이페이지")
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
                    userId = userId,
                    authViewModel = authViewModel,
                    userViewModel = userViewModel
                )
            } else {
                Home(navController = navController, mainViewModel, userViewModel, authViewModel)
            }
        }
        composable(route = Routes.BOOKSEARCH) {
            BookSearch(navController, userViewModel, bookViewModel)
            mainViewModel.changeState("검색")
        }
        composable(route = Routes.MYLIBRARY) {
            BookTotal(navController, userViewModel, bookViewModel)
            mainViewModel.changeState("내 도서관")
        }
        composable(route = Routes.CHATROOM+ "/{sellerId}",
            arguments = listOf(navArgument("sellerId"){
            type = NavType.StringType
        })) {
            entry ->
            val sellerId = entry.arguments?.getString("sellerId")
            if(sellerId != null){
                ChatRoom(sellerId)
            } else{
                Home(navController, mainViewModel, userViewModel, authViewModel)
            }
            mainViewModel.changeState("김싸피")
        }
        composable(route = Routes.REGISTER) {
            Register(navController = navController)
            mainViewModel.changeState("회원가입")
        }
        composable(route = Routes.REPORTDETAIL+"/{index}",
            arguments = listOf(navArgument("index"){
                type = NavType.IntType
            })) {
            entry ->
            val reportIndex = entry.arguments?.getInt("index");
           if(reportIndex!=null) {
               BookReportDetail(reportIndex, bookViewModel)

           }
        }
        composable(route = Routes.BOOKLIST + "/{userId}",
            arguments = listOf(navArgument("userId"){
                type = NavType.StringType
            })){entry ->
            val userId = entry.arguments?.getString("userId")
            if (userId != null) {
                BookList(navController, userViewModel, bookViewModel, userId)
            } else{
                Home(navController, mainViewModel, userViewModel, authViewModel)
            }
        }
        composable(route = Routes.BOOKLIST){
            BookList(navController, userViewModel, bookViewModel)
        }
        composable(route = Routes.BOOKTRANSACTIONREGIST){
            BookTransactionRegist(bookViewModel, navController)
        }
        composable(route = Routes.MODIFYINFO){
            Modify(navController, authViewModel, userViewModel)
        }
        composable(route = Routes.BOOKREPORTREGIST){
            BookReportRegist(bookViewModel,navController)
        }
        composable(route = Routes.BOOKISBNSEARCH+"/{route}"){
            BookIsbnSearch(bookViewModel,navController, it.arguments?.getString("route"))
        }
        composable(route = Routes.REVIEWLIST + "/{userId}",
            arguments = listOf(navArgument("userId"){
                type = NavType.StringType
            })){
            entry ->
            val userId = entry.arguments?.getString("userId")
            if (userId != null) {
                ReviewList(
                    navController,
                    userId = userId,
                    authViewModel = authViewModel,
                    userViewModel = userViewModel
                )
            } else{
                Home(navController, mainViewModel, userViewModel, authViewModel)
            }
        }
        composable(route = Routes.REVIEWLIST){
            ReviewList(navController, userViewModel, authViewModel)
        }
    }

}