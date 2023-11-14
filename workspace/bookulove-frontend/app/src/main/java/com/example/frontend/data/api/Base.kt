package com.example.frontend.data.api

import com.example.frontend.data.repository.PrefsRepository
import com.example.frontend.viewmodel.AuthViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

interface API{
    companion object{
//        private const val BASE_URL = "http://10.0.2.2:9001/api/"
        private const val REFRESH_URL = "http://10.0.2.2:9001/api/auth-service/auths/refresh"
        private const val TIMEOUT_LIMIT = 60L
        private const val BASE_URL = "https://k9c209.p.ssafy.io/api/"
        fun getInstance(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        private fun getOkHttpClient(): OkHttpClient{
            return OkHttpClient.Builder()
                .addInterceptor(AccessTokenInterceptor())
                .addInterceptor(AccessTokenExpireInterceptor())
                .build()
        }
        private class AccessTokenInterceptor : Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                val url = chain.request().url().toString()
                if(REFRESH_URL==url){
                    val refreshToken = PrefsRepository().getValue("refreshToken")
                    return if(refreshToken != ""){
                        val token = "Bearer $refreshToken"
                        val newRequest = chain.request().newBuilder().addHeader("RefreshToken", token).build()
                        chain.proceed(newRequest)
                    } else{
                        chain.proceed(chain.request())
                    }
                }else{
                    val accessToken = PrefsRepository().getValue("accessToken")
                    return if(accessToken != ""){
                        val token = "Bearer $accessToken"
                        val newRequest = chain.request().newBuilder().addHeader("Authorization", token).build()
                        chain.proceed(newRequest)
                    }else{
                        chain.proceed(chain.request())
                    }
                }
            }
        }
        private class AccessTokenExpireInterceptor: Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                val response = chain.proceed(chain.request())
                val authViewModel = AuthViewModel()
                if(response.code()==403){
                    try{
                        authViewModel.refresh()
                        val accessToken = PrefsRepository().getValue("accessToken")
                        val token = "Bearer $accessToken"
                        val newRequest = chain.request().newBuilder().addHeader("Authorization", token).build()
                        return chain.proceed(newRequest)
                    }catch (e:Exception){
                        PrefsRepository().setValue("accessToken","")
                        PrefsRepository().setValue("refreshToken","")
                    }
                }
                return response
            }
        }
    }
}