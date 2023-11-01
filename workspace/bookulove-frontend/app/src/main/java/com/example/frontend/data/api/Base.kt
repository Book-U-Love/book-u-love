package com.example.frontend.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface API{
    companion object{
        private const val BASE_URL = "http://k9c209.p.ssafy.io"


        fun getInstance(): Retrofit{
            val instance = Retrofit.
            Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

            return instance
        }

    }
}