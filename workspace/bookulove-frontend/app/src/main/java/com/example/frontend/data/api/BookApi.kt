package com.example.frontend.data.api

import com.example.frontend.data.model.BookSearchRes
import com.example.frontend.data.model.Info
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BookApi{
    @GET("book-service/books/search")
    suspend fun bookSearch(@Query("isbn") isbn:String):Response<Info>


}