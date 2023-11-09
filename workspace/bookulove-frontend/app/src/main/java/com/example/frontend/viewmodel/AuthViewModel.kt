package com.example.frontend.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.frontend.data.model.Certification
import com.example.frontend.data.model.PhoneNumber
import com.example.frontend.data.model.User
import com.example.frontend.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel(){
    private val authRepository: AuthRepository = AuthRepository()

    fun logIn(user: User){
        viewModelScope.launch{
//            authRepository.logIn(user, response)
        }
    }

    fun sendCertification(phoneNumber: PhoneNumber){
        viewModelScope.launch{
//            authRepository.sendCertification(phoneNumber, response)
        }
    }

    fun checkCertification(certification: Certification){
        viewModelScope.launch {
//            authRepository.checkCertification(certification, response)
        }
    }
}

class AuthViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel() as T
        }
        throw IllegalArgumentException("unknown viewModel");
    }
}