package com.example.frontend.data.api

import com.example.frontend.data.model.Book
import com.example.frontend.data.model.BookRegistReq
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BookApi{
    @GET("book-service/books/search")
    suspend fun getBookSearchList(@Query("isbn") isbn:String):Response<Book>

//    @POST("book-service/books")
//    suspend fun registBook(@Body bookRegistReq:BookRegistReq):Response<>
}