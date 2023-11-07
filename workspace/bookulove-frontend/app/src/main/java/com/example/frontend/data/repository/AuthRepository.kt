package com.example.frontend.data.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.frontend.data.api.API
import com.example.frontend.data.api.AuthApi
import com.example.frontend.data.model.User

class AuthRepository(
    private val api: AuthApi = API.getInstance().create(AuthApi::class.java)
) {

    suspend fun logIn(user: User, result: MutableState<String>){
        val response = api.logIn(user)
        if(response.body()!!.status == 200){
            result.value = "Success"
        } else{
            result.value = "Fail"
        }
    }
}