package com.example.frontend.data.model

data class BookRes(
    val status: Number,
    val code: String,
    val info:Info,
)
sealed class Info(){
    data class BookSearchRes(
        val bookId: Number,
        val isbn: String,
        val title: String,
        val author: String,
        val publisher: String,
        val pubDate: String,
        val category: String,
        val price: Number,
    ):Info()
    data class BookRegistReq(
        val isbn:String,
        val condition:Int,
        val allowSale:Boolean,
        val allowBorrow:Boolean,
        val details:String,
    )
}


data class BookSearchRes(
    val bookId: Number,
    val isbn: String,
    val title: String,
    val author: String,
    val publisher: String,
    val pubDate: String,
    val category: String,
    val price: Number,
)
