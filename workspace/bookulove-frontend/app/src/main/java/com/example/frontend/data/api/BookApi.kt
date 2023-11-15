package com.example.frontend.data.api

import com.example.frontend.data.model.BookList
import com.example.frontend.data.model.BookReportReq
import com.example.frontend.data.model.BookReportRes
import com.example.frontend.data.model.BookSearchRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BookApi{
    @GET("book-service/books/search")
    suspend fun bookSearch(@Query("isbn") isbn:String):Response<BookSearchRes>

    @POST("book-service/reviews")
    suspend fun reportRegist(@Body reportInfo:BookReportReq):Response<BookReportRes>

    @GET("book-service/books/list")
    suspend fun getBookList(@Query("sale") sale:Boolean, @Query("borrow") borrow: Boolean): Response<BookList>

}