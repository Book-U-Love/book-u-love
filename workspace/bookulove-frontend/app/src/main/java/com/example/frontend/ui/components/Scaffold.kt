package com.example.frontend.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.frontend.R
import com.example.frontend.ui.vo.Routes

@OptIn(ExperimentalMaterial3Api::class)
// [START android_compose_components_scaffold]
@Composable
fun Scaffold() {
    var curRoute by remember { mutableStateOf(Routes.HOME) }

    Scaffold(
        topBar = {
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
                scrollBehavior = pinnedScrollBehavior()
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Row(modifier = Modifier
                    .padding(25.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween){
                    IconButton(onClick = {curRoute = Routes.HOME}) {
                        Icon(painter = painterResource(id = R.drawable.baseline_home_24), contentDescription = "home")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_chat_bubble_outline_24), contentDescription = "chat")
                    }
                    IconButton(onClick = {curRoute = Routes.BOOKSEARCH }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_search_24), contentDescription = "search")
                    }
                    IconButton(onClick = {curRoute = Routes.MYPAGE}) {
                        Icon(painter = painterResource(id = R.drawable.baseline_settings_24), contentDescription = "settings")
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {  }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

        }
    }
}
