package com.example.frontend.data.repository

import android.util.Log
import com.example.frontend.data.api.API
import com.example.frontend.data.api.BookApi
import com.example.frontend.data.model.BookRegistReq
import com.example.frontend.data.model.BookReportReq
import com.example.frontend.data.model.BookResult
import com.example.frontend.data.model.MyBookListRes
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
    suspend fun bookRegist(bookInfo: BookRegistReq):Flow<Boolean> = flow{
        val response = api.bookRegist(bookInfo)
        Log.d("response res", "res start")
        if(response.code()==200){
            emit(true)
            Log.d("res","res 성공")
        }else{
            emit(false)
            Log.d("res","res 실패")
        }
    }
    suspend fun getMyBookList():Flow<List<Map<String,String>>> = flow{
        val response = api.getMyBookList()
        Log.d("mybook res", "Res start")
        if(response.code()==200){
            response.body()?.let {
                Log.d("response list",response.body()!!.data.toString())
                emit(response.body()!!.data)
            }
            Log.d("book list res"," book list 성공")
        }else{
            Log.d("res", "Res 실패")
        }
    }

    suspend fun getBookList(sale: Boolean, borrow: Boolean) = flow<List<Map<String, String>>>{
        val response = api.getBookList(sale, borrow)
        if(response.body()!!.status == 200){
            emit(response.body()!!.data)
        }
    }

    suspend fun getUserBookList(userId: String, sale: Boolean, borrow: Boolean) = flow<List<Map<String, String>>>{
        val response = api.getUserBookList(userId, sale, borrow)
        if(response.body()!!.status == 200){
            emit(response.body()!!.data)
        }
    }

    suspend fun getMyReportList() = flow<List<Map<String, String>>>{
        val response = api.getMyReportList()
        if(response.body()!!.status == 200){
            emit(response.body()!!.data)
        }
    }

    suspend fun getReport(reviewId: String) = flow<Map<String, String>>{
        val response = api.getReview(reviewId)
        if(response.body()!!.status == 200){
            emit(response.body()!!.data)
        }
    }

    suspend fun bookSearchIsbn(isbn: String) = flow<BookResult>{
        val response = api.bookSearchIsbn(isbn)
        if(response.body()!!.status == 200){
            emit(response.body()!!.data)
        }
    }
}