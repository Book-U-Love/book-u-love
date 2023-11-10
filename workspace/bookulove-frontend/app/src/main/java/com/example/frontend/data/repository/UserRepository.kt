package com.example.frontend.data.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import com.example.frontend.data.api.API
import com.example.frontend.data.api.UserApi
import com.example.frontend.data.model.User
import com.example.frontend.data.model.UserRegistDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class UserRepository(
    private val api: UserApi = API.getInstance().create(UserApi::class.java)
) {
    suspend fun signUp(userInfo:UserRegistDto):Flow<String> = flow{
        val response = api.signUp(userInfo)
        Log.i("find", response.body()!!.toString())
        if(response.body()!!.status == 200){
            emit("success")
        } else{
            emit("fail")
        }

}
}