package com.example.frontend.data.api

import com.example.frontend.data.model.Default
import com.example.frontend.data.model.MapData
import com.example.frontend.data.model.ModifyPw
import com.example.frontend.data.model.ModifyUser
import com.example.frontend.data.model.User
import com.example.frontend.data.model.UserRegistDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST


interface UserApi {
    @POST("user-service/users")
    suspend fun signUp(@Body userInfo:UserRegistDto):Response<Default>

    @GET("user-service/users")
    suspend fun getInfo(@Header("Authorization") token: String):Response<MapData>

    @PATCH("user-service/users")
    suspend fun modifyUser(@Header("Authorization") token: String, @Body modifyUser: ModifyUser):Response<Default>

    @PATCH("user-service/users/password")
    suspend fun modifyPassword(@Header("Authorization") token: String, @Body modifyPw: ModifyPw): Response<Default>
}