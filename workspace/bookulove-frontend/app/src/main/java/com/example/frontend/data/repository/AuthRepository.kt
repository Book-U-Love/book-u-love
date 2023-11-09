package com.example.frontend.data.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.frontend.data.api.API
import com.example.frontend.data.api.AuthApi
import com.example.frontend.data.model.Certification
import com.example.frontend.data.model.PhoneNumber
import com.example.frontend.data.model.User

class AuthRepository(
    private val api: AuthApi = API.getInstance().create(AuthApi::class.java)
) {

    suspend fun logIn(user: User, result: MutableState<String>){
        val response = api.logIn(user)
        Log.d("find", response.body()!!.toString())
        if(response.body()!!.code == "OK"){
            result.value = "Success"
        } else{
            result.value = "Fail"
        }
    }

    suspend fun sendCertification(phoneNumber: PhoneNumber, result: MutableState<String>){
        val response = api.sendCertification(phoneNumber)
        if(response.body()!!.code == "OK"){
            result.value = "Success"
        } else{
            result.value = "Fail"
        }
    }

    suspend fun checkCertification(certification: Certification, result: MutableState<String>){
        val response = api.checkCertification(certification)
        if(response.body()!!.code == "OK"){
            result.value = "Success"
        } else{
            result.value = "Fail"
        }
    }
}