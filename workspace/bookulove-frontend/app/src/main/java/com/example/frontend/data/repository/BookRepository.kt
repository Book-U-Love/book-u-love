package com.example.frontend.data.repository

import com.example.frontend.data.api.API
import com.example.frontend.data.api.BookApi
import com.example.frontend.data.model.Book
import java.util.concurrent.Flow

class BookRepository(
    private val api: BookApi = API.getInstance().create(BookApi::class.java)
) {
//    fun bookSearch(isbn:String): Flow<Book> = flow{
//
//    }
}