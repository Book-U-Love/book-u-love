package com.example.frontend.data.repository

import android.util.Log
import com.example.frontend.data.api.API
import com.example.frontend.data.api.BookApi
import com.example.frontend.data.model.BookReportReq
import com.example.frontend.data.model.BookReportRes
import com.example.frontend.data.model.BookSearchRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
class BookRepository(
    private val api: BookApi = API.getInstance().create(BookApi::class.java)
) {
    suspend fun bookSearch(isbn:String) = flow<Map<String,String>>{
        val response = api.bookSearch(isbn)
        Log.d("resbody",response.code().toString())
        if(response.code() == 200){
            response.body()?.let{
                emit(response.body()!!.data)
            }
        } else{
            Log.d("resbody",response.toString())
            emit(response.body()!!.data)
        }
    }
    suspend fun reportRegist(reportInfo: BookReportReq):Flow<Boolean> = flow{
        val response = api.reportRegist(reportInfo)
        Log.d("getres","getres")
        if(response.code()==200){
            emit(true)
            Log.d("res success","res sucess")
        }else{
            Log.d("res fail", "res fail")
            emit(false)
        }
    }
}