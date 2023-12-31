package com.example.frontend.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.frontend.data.model.Certification
import com.example.frontend.data.model.PhoneNumber
import com.example.frontend.data.model.User
import com.example.frontend.data.repository.AuthRepository
import com.example.frontend.data.repository.PrefsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class AuthViewModel(): ViewModel(){
    private val authRepository: AuthRepository = AuthRepository()
    private val _loginRes: MutableStateFlow<String> = MutableStateFlow("init")
    private val _certChkRes: MutableStateFlow<String> = MutableStateFlow("init")
    private val _certSendRes: MutableStateFlow<String> = MutableStateFlow("init")

    val loginRes : StateFlow<String>
        get() = _loginRes
    val certChkRes : StateFlow<String>
        get() = _certChkRes
    val certSendRes : StateFlow<String>
        get() = _certSendRes

    fun resetState(){
        _loginRes.value = "init"
    }

    fun logIn(user: User){
        GlobalScope.async{
            try{
                authRepository.logIn(user).collect(){
                    res ->
                    _loginRes.value = res
                }
            } catch (e:Exception){
                _loginRes.value = "fail"
            }
        }
    }
    fun refresh(){
        GlobalScope.async{
            try{
                authRepository.refresh().collect(){
                    res-> Log.d("refresh",res.toString())
                }
            }catch (e:Exception){
                Log.d("refresh","refresh fail")
            }
        }
    }
    fun sendCertification(phoneNumber: PhoneNumber){
        GlobalScope.async {
            try {
                authRepository.sendCertification(phoneNumber).collect(){
                    res ->
                    _certSendRes.value = res
                }
            } catch (e:Exception){
            }
        }
    }

    fun checkCertification(certification: Certification){
        GlobalScope.async {
            try {
                authRepository.checkCertification(certification).collect(){
                    res ->
                    _certChkRes.value = res
                }
            } catch (e:Exception){

            }
        }
    }
}

class AuthViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel() as T
        }
        throw IllegalArgumentException("unknown viewModel");
    }
}