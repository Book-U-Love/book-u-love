package com.example.frontend.data.api

import com.example.frontend.data.model.Book
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi{
    @GET("book-service/books/search")
    suspend fun getBookSearchList(@Query("isbn") isbn:String):Response<Book>

}