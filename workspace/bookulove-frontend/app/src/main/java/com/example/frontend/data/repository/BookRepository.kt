package com.example.frontend.data.repository

import android.util.Log
import com.example.frontend.data.api.API
import com.example.frontend.data.api.BookApi
import kotlinx.coroutines.flow.flow
class BookRepository(
    private val api: BookApi = API.getInstance().create(BookApi::class.java)
) {
    fun bookSearch(isbn:String) = flow<Boolean>{
        Log.d("searchfail repo1","searchfail repo")
        val response = api.bookSearch(isbn)
        Log.d("book repo code", response.code().toString())

        Log.d("book repo",response.body()!!.toString())
        if(response.code() == 200){
            Log.d("book repo res code","book repo res code")
            response.body()?.let{
               Log.d("booksearch",response.body().toString())
            }
        } else{
            Log.d("searchfail repo","searchfail repo")
        }
    }
}