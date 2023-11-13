package com.example.frontend.data.api

import com.example.frontend.data.model.Certification
import com.example.frontend.data.model.Default
import com.example.frontend.data.model.MapData
import com.example.frontend.data.model.PhoneNumber
import com.example.frontend.data.model.Token
import com.example.frontend.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.HTTP
import retrofit2.http.POST

interface AuthApi {
    @POST("auth-service/auths")
    suspend fun logIn(@Body user: User): Response<Token>

    @POST("auth-service/sms")
    suspend fun sendCertification(@Body phoneNumber: PhoneNumber): Response<Default>

    @HTTP(method="DELETE", path="auth-service/sms", hasBody = true)
    suspend fun checkCertification(@Body certification: Certification): Response<Default>

    @POST("auth-service/refresh")
    suspend fun refresh() : Response<String>
}