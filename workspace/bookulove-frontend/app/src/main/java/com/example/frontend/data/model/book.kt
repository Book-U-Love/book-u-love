package com.example.frontend.data.model


data class Book(
    val bookId: Number,
    val isbn: String,
    val title: String,
    val author: String,
    val publisher: String,
    val pubDate: String,
    val category: String,
    val price: Number,
)