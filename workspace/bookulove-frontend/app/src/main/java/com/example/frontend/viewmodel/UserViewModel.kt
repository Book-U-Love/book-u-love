package com.example.frontend.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.frontend.data.model.UserRegistDto
import com.example.frontend.data.repository.UserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.concurrent.Flow

class UserViewModel: ViewModel(){
    private val userRepository: UserRepository = UserRepository()

    private val _signupRes: MutableStateFlow<String> = MutableStateFlow("init")
    val signupRes : StateFlow<String>
        get() = _signupRes

    fun signUp(userInfo: UserRegistDto){
        GlobalScope.async{
            try{
                userRepository.signUp(userInfo).collect(){
                    res->
                    _signupRes.value = res;
                }
            }catch (e:Exception){
            }
        }
    }
}

class UserViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel() as T
        }
        throw IllegalArgumentException("unknown viewModel");
    }
}