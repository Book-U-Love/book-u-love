package com.example.frontend.data.repository

import com.example.frontend.data.api.API
import com.example.frontend.data.api.BookApi
import com.example.frontend.data.model.Book
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

class BookRepository(
    private val api: BookApi = API.getInstance().create(BookApi::class.java)
) {
    fun getBookSearchList(isbn:String) = flow<Boolean>{
        val response = api.getBookSearchList(isbn)
        if(response.code()==200){

        }
    }
}