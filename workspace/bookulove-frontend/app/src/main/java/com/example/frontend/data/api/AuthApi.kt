package com.example.frontend.data.api

import com.example.frontend.data.model.Default
import com.example.frontend.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth-service/auths")
    suspend fun logIn(@Body user: User): Response<Default>
}