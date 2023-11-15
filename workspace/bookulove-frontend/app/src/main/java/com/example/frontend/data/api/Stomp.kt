package com.example.frontend.data.api

import com.example.frontend.data.repository.PrefsRepository
import com.example.frontend.viewmodel.AuthViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import java.util.concurrent.TimeUnit

object StompSingleton{
        private const val BASE_URL = "wss://k9c209.p.ssafy.io/api/chatting-service/stomps"
        private const val REFRESH_URL = "https://k9c209.p.ssafy.io/api/auth-service/auths/refresh"
        private lateinit var socket: OkHttpClient
        private lateinit var stomp : StompClient
        private lateinit var ac :String
        object OKHTTPSingleton{
            val instance:OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(AccessTokenInterceptor())
                .addInterceptor(AccessTokenExpireInterceptor())
                .pingInterval(100, TimeUnit.SECONDS)
                .build()
        }
        fun init(){
            socket = OKHTTPSingleton.instance
            ac = PrefsRepository().getValue("accessToken")
            stomp = Stomp.over(Stomp.ConnectionProvider.OKHTTP, BASE_URL, mapOf("Authorization" to "$ac"), socket)
        }
        fun getStompClient():StompClient{
            return stomp
        }
    private class AccessTokenInterceptor : Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            val url = chain.request().url().toString()
            if(REFRESH_URL ==url){
                val refreshToken = PrefsRepository().getValue("refreshToken")
                return if(refreshToken != ""){
                    val token = "$refreshToken"
                    val newRequest = chain.request().newBuilder().addHeader("RefreshToken", token).build()
                    chain.proceed(newRequest)
                } else{
                    chain.proceed(chain.request())
                }
            }else{
                val accessToken = PrefsRepository().getValue("accessToken")
                return if(accessToken != ""){
                    val token = "$accessToken"
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
                        val token = "$accessToken"
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