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
import java.io.IOException
import kotlin.reflect.typeOf

class AuthRepository(
    private val api: AuthApi = API.getInstance().create(AuthApi::class.java)
) {
    @Throws(IOException::class)
    suspend fun logIn(user: User): Flow<String> = flow{
        val response = api.logIn(user)
        if(response.code() == 200){
            response.body()?.let{
                PrefsRepository().setValue("accessToken",response.body()!!.data["accessToken"].toString())
                PrefsRepository().setValue("refreshToken",response.body()!!.data["refreshToken"].toString())
            }
            emit("success")
        } else{
            emit("fail")
        }
    }
    suspend fun refresh(): Flow<Boolean> = flow{
        val response = api.refresh()
        if(response.code()==200){
            PrefsRepository().setValue("accessToken",response.body().toString())
            emit(true)
        }else{
            emit(false)
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