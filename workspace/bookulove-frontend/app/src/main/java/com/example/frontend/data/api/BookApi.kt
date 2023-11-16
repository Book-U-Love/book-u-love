package com.example.frontend.data.api

import com.example.frontend.data.model.BookRegistReq
import com.example.frontend.data.model.BookRegistRes
import com.example.frontend.data.model.BookList
import com.example.frontend.data.model.BookReportReq
import com.example.frontend.data.model.BookReportRes
import com.example.frontend.data.model.BookResult
import com.example.frontend.data.model.BookSearchRes
import com.example.frontend.data.model.MapData
import com.example.frontend.data.model.MyBookListRes
import com.example.frontend.data.model.ReportList
import com.example.frontend.data.model.SearchResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApi{
    @GET("book-service/books/search")
    suspend fun bookSearch(@Query("isbn") isbn:String):Response<BookSearchRes>

    @POST("book-service/reviews")
    suspend fun reportRegist(@Body reportInfo:BookReportReq):Response<BookReportRes>

    @GET("book-service/books/list")
    suspend fun getBookList(@Query("sale") sale:Boolean, @Query("borrow") borrow: Boolean): Response<BookList>

    @GET("book-service/books/list/{userId}")
    suspend fun getUserBookList(@Path("userId") userId: String, @Query("sale") sale: Boolean, @Query("borrow") borrow: Boolean): Response<BookList>

    @GET("book-service/reviews")
    suspend fun getMyReportList(): Response<ReportList>

    @GET("book-service/reviews/{reviewId}")
    suspend fun getReview(@Path("reviewId") reviewId: String): Response<MapData>

    @POST("book-service/books")
    suspend fun bookRegist(@Body bookInfo:BookRegistReq):Response<BookRegistRes>

    @GET("book-service/books")
    suspend fun bookSearchIsbn(@Query("isbn") isbn: String): Response<SearchResult>

    @GET("book-service/books/list?sale=false&borrow=false")
    suspend fun getMyBookList():Response<MyBookListRes>
}