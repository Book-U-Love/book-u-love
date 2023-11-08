package com.example.frontend.data.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.frontend.data.api.API
import com.example.frontend.data.api.UserApi
import com.example.frontend.data.model.User
import com.example.frontend.data.model.UserRegistDto


class UserRepository(
    private val api: UserApi = API.getInstance().create(UserApi::class.java)
) {
    suspend fun signUp(userInfo:UserRegistDto, result: MutableState<String>){
        val response = api.signUp(userInfo)
        if(response.body()!!.status == 200){
            result.value = "Success"
        } else{
            result.value = "Fail"
        }
}
}