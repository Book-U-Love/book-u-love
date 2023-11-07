package com.example.frontend.data.api

import com.example.frontend.data.model.Default
import com.example.frontend.data.model.User
import com.example.frontend.data.model.UserRegistDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface UserApi {
    @POST("user-service/users")
    suspend fun signUp(@Body userInfo:UserRegistDto):Response<Default>

    @POST("auth-service/auths")
    suspend fun logIn(@Body user:User):Response<Default>
}