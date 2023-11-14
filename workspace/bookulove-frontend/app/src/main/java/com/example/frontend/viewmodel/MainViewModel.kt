package com.example.frontend.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel: ViewModel(){
    private val _navState = mutableStateOf("")
    private val _isLogin = mutableStateOf(false)
    val navState: State<String> = _navState
    val isLogin: State<Boolean> = _isLogin
    fun changeState(state:String){
        _navState.value = state
    }

    fun changeLoginState(state: Boolean){
        _isLogin.value = state
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