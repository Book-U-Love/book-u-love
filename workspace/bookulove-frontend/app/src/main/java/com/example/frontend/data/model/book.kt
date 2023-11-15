package com.example.frontend.data.model


data class BookRegistReq(
    val isbn:String,
    val condition:Int,
    val allowSale:Boolean,
    val allowBorrow:Boolean,
    val details:String, )


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
