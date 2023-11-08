package com.example.frontend.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.frontend.data.model.UserRegistDto
import com.example.frontend.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel: ViewModel(){
    private val userRepository: UserRepository = UserRepository()

    fun signUp(userInfo: UserRegistDto, response : MutableState<String>){
        viewModelScope.launch{
            userRepository.signUp(userInfo, response)
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