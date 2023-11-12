package com.example.frontend.data.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import com.example.frontend.data.api.API
import com.example.frontend.data.api.UserApi
import com.example.frontend.data.model.ModifyPw
import com.example.frontend.data.model.ModifyUser
import com.example.frontend.data.model.User
import com.example.frontend.data.model.UserRegistDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class UserRepository(
    private val api: UserApi = API.getInstance().create(UserApi::class.java)
) {
    suspend fun signUp(userInfo:UserRegistDto):Flow<String> = flow{
        val response = api.signUp(userInfo)
        Log.d("find", response.body()!!.toString())
        if(response.body()!!.status == 200){
            emit("success")
        } else{
            emit("fail")
        }
    }
    suspend fun getInfo(token: String):Flow<Map<String, String>> = flow{
        val response = api.getInfo(token)
        Log.d("find", response.toString())
        if(response.body()!!.status == 200){
            val res = response.body()!!.data
            emit(mapOf("msg" to "success", "loginId" to res.get("loginId").toString(),
                "nickname" to res.get("nickname").toString(), "phoneNumber" to res.get("phoneNumber").toString(), "libraryName" to res.get("libraryName").toString(),
                "lat" to res.get("lat").toString(), "lng" to res.get("lng").toString()
            ))
        } else{
            emit(mapOf("msg" to "fail"))
        }
    }

    suspend fun modifyUser(token: String, modifyUser: ModifyUser):Flow<String> = flow{
        val response = api.modifyUser(token, modifyUser)
        Log.d("find", response.body()!!.toString())
        if(response.body()!!.status == 200){
            emit("success")
        } else{
            emit("fail")
        }
    }

    suspend fun modifyPassword(token: String, modifyPw: ModifyPw):Flow<String> = flow{
        val response = api.modifyPassword(token, modifyPw)
        if(response.body()!!.status == 200){
            emit("success")
        } else{
            emit("fail")
        }
    }
}
