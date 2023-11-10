package com.example.frontend.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun Message(
    title: String,
    dialogClose: () -> Unit,
    confirmButton: () -> Unit = dialogClose,
    content: String
){
    AlertDialog(
        onDismissRequest = { dialogClose() },
        confirmButton = {
            TextButton(onClick = { confirmButton() }) {
                Text(text = "확인")
            }
        },
        title = {Text(title)},
        text = {Text(content)},
    )
}