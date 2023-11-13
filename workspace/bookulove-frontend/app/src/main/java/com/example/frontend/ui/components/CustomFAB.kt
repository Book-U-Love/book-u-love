package com.example.frontend.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.frontend.R
import com.example.frontend.ui.vo.Routes

@Composable
fun CustomFAB(
    modifier: Modifier,
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

    Box(modifier=modifier){
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Column(){
                if(isExpanded){
                    Column {
                        ExtendedFloatingActionButton(
                            onClick = {navController.navigate(Routes.BOOKREGIST)},
                            modifier= Modifier
                                .width(expandedWidth)
                                .height(expandedHeight),
                        ) {
                            Text("도서 등록")
                        }
                        ExtendedFloatingActionButton(
                            onClick = { /*TODO*/ },
                            modifier= Modifier
                                .padding(bottom = 15.dp)
                                .width(expandedWidth)
                                .height(expandedHeight),
                        ) {
                            Text("독후감 등록")
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



