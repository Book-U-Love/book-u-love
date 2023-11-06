package com.example.frontend.data.repository

import android.util.Log
import com.example.frontend.data.api.API
import com.example.frontend.data.api.UserApi
import com.example.frontend.data.model.UserRegistDto


class UserRepository(
    private val api: UserApi = API.getInstance().create(UserApi::class.java)
) {
    suspend fun signUp(userInfo:UserRegistDto){
        val response = api.signUp(userInfo)
        if(response.code()==200){

        }
    }
}