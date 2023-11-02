package com.example.frontend.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

class MainViewModel: ViewModel(){
    private val _navState = mutableStateOf("")

    val navState: State<String> = _navState

    fun changeState(state:String){
        _navState.value = state
    }
}

class MainViewModelFactory(): ViewModelProvider.Factory{
    override fun <T:ViewModel> create(modelClass:Class<T>):T{
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel() as T
        }
        throw IllegalArgumentException("unknown viewModel");
    }
}