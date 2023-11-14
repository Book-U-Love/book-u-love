package com.example.frontend.viewmodel

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookViewModel: ViewModel() {
    private val _reportState = mutableStateOf(true)
    private val _isExpanded = mutableStateOf(false)
    val reportState: State<Boolean> = _reportState
    val isExpanded: State<Boolean> = _isExpanded

    fun changeState(){
        _reportState.value = !_reportState.value
    }
    fun changeExpandState(){
        _isExpanded.value = !_isExpanded.value
    }
}
class BookViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            return BookViewModel() as T
        }
        throw IllegalArgumentException("unknown viewModel");
    }
}