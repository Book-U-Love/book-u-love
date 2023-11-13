package com.example.frontend.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.frontend.R
import com.example.frontend.ui.vo.Routes

@Composable
fun CustomFAB(
    navController: NavController
){
    var isExpanded by remember{
        mutableStateOf(false)
    }
    var fabSize  = 64.dp
    val expandedWidth by animateDpAsState(
        targetValue = if(isExpanded) 150.dp else fabSize,
        animationSpec = spring(dampingRatio=3f),
        label="expandedWidth"
    )
    val expandedHeight by animateDpAsState(
        targetValue = if(isExpanded) 58.dp else fabSize,
        animationSpec = spring(dampingRatio = 3f),
        label="expandedHeight"
    )
    val rotate by animateFloatAsState(
        targetValue = if(isExpanded) 45f else 0f,
        animationSpec = spring(dampingRatio = 3f),
        label="floatingButtonRotate")

    Box(modifier= Modifier
        .fillMaxSize()
        .background(
            when (isExpanded) {
                true -> Color.Black.copy(alpha = 0.3f); false -> Color.Transparent
            }
        )
        .padding(bottom = 15.dp, end = 15.dp),
        Alignment.BottomEnd
    ){
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Column(){
                if(isExpanded){
                    Column(modifier=Modifier.padding(bottom = 15.dp)){

                        Box(modifier = Modifier.background(Color.White, RoundedCornerShape(5.dp))
                            ){
                            Column(modifier=Modifier.background(Color.White,RoundedCornerShape(5.dp))){
                                Box(modifier = Modifier
                                    .background(Color.White, RoundedCornerShape(5.dp))
                                    .width(expandedWidth)
                                    .height(expandedHeight)
                                    .clickable { navController.navigate(Routes.BOOKTRANSACTIONREGIST) }){
//                                    Icon(painter = painterResource(id = R.drawable.baseline_sell_24), contentDescription = "sell",modifier=Modifier.align(Alignment.Center))
                                    Text("판매도서 등록",modifier=Modifier.align(Alignment.Center))
                                }
                                Box(modifier = Modifier
                                    .background(Color.White, RoundedCornerShape(5.dp))
                                    .width(expandedWidth)
                                    .height(expandedHeight)
                                    .clickable { navController.navigate(Routes.BOOKREPORTREGIST) }){
//                                    Icon(painter = painterResource(id = R.drawable.baseline_book_24), contentDescription = "report", modifier=Modifier.align( Alignment.Center))
                                    Text("독후감 등록",modifier=Modifier.align(Alignment.Center))
                                }
                            }

                        }
                    }
                }
                ExtendedFloatingActionButton(
                    onClick = {isExpanded=!isExpanded;},
                    ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "add",
                        modifier=Modifier.rotate(rotate))

                }
            }
    }


    }

}



