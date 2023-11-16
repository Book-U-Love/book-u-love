package com.example.frontend.data.model

import androidx.collection.ArrayMap


data class BookRegistReq(
    val isbn:String,
    val condition:Number,
    val allowSale:Boolean,
    val allowBorrow:Boolean,
    val details:String,
)
data class BookRegistRes(
    val status: Number,
    val code : String,
    val data : String,
)

data class BookReportReq(
    val bookId:Number,
    val title : String,
    val content: String,
)
data class BookReportRes(
    val status: Number,
    val code : String,
    val data : String,
)
data class BookSearchRes(
   val status: Number,
    val code : String,
    val data : Map<String, String>
)
data class MyBookListRes(
    val status : Number,
    val code:String,
    val data: List<Map<String,String>>
)

data class BookList(
    val status: Int,
    val code: String,
    val data: List<Map<String, String>>
)

data class ReportList(
    val status: Int,
    val code: String,
    val data: List<Map<String, String>>
)

data class SearchResult(
    val status: Int,
    val code: String,
    val data: BookResult
)

data class BookResult(
    val bookId: Int = 0,
    val title: String = "",
    val author: String = "",
    val publisher: String = "",
    val price: Int = 0,
    val pubDate: String = "",
    val cover: String = "",
    val reviewInfoResList: List<Map<String, String>> = listOf(),
    val saleBookInfoList: List<Map<String, String>> = listOf(),
    val borrowBookInfoList: List<Map<String, String>> = listOf(),
)