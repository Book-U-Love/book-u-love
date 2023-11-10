package com.example.frontend.data.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.frontend.data.api.API
import com.example.frontend.data.api.AuthApi
import com.example.frontend.data.model.Certification
import com.example.frontend.data.model.PhoneNumber
import com.example.frontend.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepository(
    private val api: AuthApi = API.getInstance().create(AuthApi::class.java)
) {

    suspend fun logIn(user: User): Flow<String> = flow{
        val response = api.logIn(user)
        if(response.body()!!.code == "OK"){
            emit("success")
        } else{
            emit("fail")
        }
    }

    suspend fun sendCertification(phoneNumber: PhoneNumber): Flow<String> = flow{
        val response = api.sendCertification(phoneNumber)
        if(response.body()!!.code == "OK"){
            emit("success")
        } else{
            emit("success")
        }
    }

    suspend fun checkCertification(certification: Certification): Flow<String> = flow{
        val response = api.checkCertification(certification)
        if(response.body()!!.code == "OK"){
            emit("success")
        } else{
            emit("fail")
        }
    }
}