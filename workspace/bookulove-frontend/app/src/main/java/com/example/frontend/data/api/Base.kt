package com.example.frontend.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface API{
    companion object{
        private const val BASE_URL = "http://10.0.2.2:9001/api/"
        private const val SFY_URL = "https://k9c209.p.ssafy.io/api/"
        fun getInstance(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}