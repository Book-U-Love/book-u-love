package com.example.frontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.frontend.R

@Composable
fun DropDown(
    itemList:List<String>,
    conditionValue: String,
    onValueChanged:(String)->Unit
){
    var dropState = remember{
        mutableStateOf(false)
    }
    var index = remember{
        mutableStateOf(-1)
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .border(
            1.dp, Color.Black.copy(alpha = 0.5f),
            RoundedCornerShape(5.dp)
        )
        .clickable { dropState.value = !dropState.value }
        .padding(15.dp)){
        Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween){
            Text(when(index.value>=0){true-> itemList.get(index.value);false->"선택"})
            Icon(painter = painterResource(id =
            when(dropState.value){true -> R.drawable.baseline_arrow_drop_up_24; false->R.drawable.baseline_arrow_drop_down_24}), contentDescription = "arrow")
        }

        DropdownMenu(modifier= Modifier
            .background(Color.Transparent)
            .padding(15.dp)
            .fillMaxWidth()
            .background(Color.White),expanded = dropState.value, onDismissRequest = { dropState.value=false }) {
                itemList.forEachIndexed{
                    i, item -> DropdownMenuItem(
                                        text = { Text(item) },
                                        onClick = { dropState.value = !dropState.value;
                                                    onValueChanged(item)
                                                    index.value=i

                    })

                }

        }

    }
    
}


