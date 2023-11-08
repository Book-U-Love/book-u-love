package com.example.frontend.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.frontend.data.model.User
import com.example.frontend.data.model.UserRegistDto
import com.example.frontend.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

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