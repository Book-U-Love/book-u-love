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
        if(response.body()!!.status == 200){
            emit("success")
        } else{
            emit("fail")
        }
    }
    suspend fun getMyInfo():Flow<Map<String, String>> = flow{
        val response = api.getMyInfo()
        Log.d("find r", response.body()!!.toString())
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

    suspend fun getMyPage():Flow<Map<String, String>> = flow{
        val response = api.getMyPage()
        if(response.body()!!.status == 200){
            emit(response.body()!!.data)
        }
    }

    suspend fun getUserPage(userId: String):Flow<Map<String, String>> = flow{
        val response = api.getUserPage(userId)
        Log.d("find result", response.body()!!.data.toString())
        if(response.body()!!.status == 200){
            emit(response.body()!!.data)
        }
    }

    suspend fun modifyUser(modifyUser: ModifyUser):Flow<String> = flow{
        val response = api.modifyUser( modifyUser)
        if(response.body()!!.status == 200){
            emit("success")
        } else{
            emit("fail")
        }
    }

    suspend fun modifyPassword(modifyPw: ModifyPw):Flow<String> = flow{
        val response = api.modifyPassword(modifyPw)
        if(response.body()!!.status == 200){
            emit("success")
        } else{
            emit("fail")
        }
    }

    suspend fun getLibraryList():Flow<List<Map<String, String>>> = flow{
        val response = api.getLibraryList()
        val list: List<Map<String, String>>? = response.body()!!.data.get("userFindInfoResList")
        if(response.body()!!.status == 200){
            if(list != null){
                emit(list)
            }
        }
    }

    suspend fun checkId(userId: String): Flow<String> = flow{
        val response = api.checkId(userId)
        if(response.body()!!.status == 200){
            emit("success")
        } else{
            emit("fail")
        }
    }

    suspend fun getReviewList(): Flow<List<Map<String, String>>> = flow{
        val response = api.getReviewList()
        if(response.body()!!.status == 200){
            val list: List<Map<String, String>>? = response.body()!!.data.get("revieweeDomainList")
            if(list != null){
                emit(list)
            }
        } else{
        }
    }
}
