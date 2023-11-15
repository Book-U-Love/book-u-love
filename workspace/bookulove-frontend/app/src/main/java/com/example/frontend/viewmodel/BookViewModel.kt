package com.example.frontend.viewmodel

import android.util.ArrayMap
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.frontend.data.model.BookRegistReq
import com.example.frontend.data.model.BookReportReq
import com.example.frontend.data.repository.BookRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import java.lang.Exception

class BookViewModel: ViewModel() {
    private val bookRepository:BookRepository = BookRepository();
    private val _reportState = mutableStateOf(true)
    val reportState: State<Boolean> = _reportState
    private val _isExpanded = mutableStateOf(false)
    val isExpanded: State<Boolean> = _isExpanded
    // isbn 검색 여부
    private val _isbnSearchState = mutableStateOf(false)
    val isbnSearchState = _isbnSearchState
    // isbn 검색 결과
    private val _bookSearchRes = MutableStateFlow<Map<String, String>?>(emptyMap())
    val bookSearchRes: StateFlow<Map<String, String>?> = _bookSearchRes
    // 독후감 등록 결과
    private val _reportRegistRes = MutableStateFlow<Boolean?>(null)
    val reportRegistRes : StateFlow<Boolean?> = _reportRegistRes
    // 책 등록 결과
    private val _bookRegistRes = MutableStateFlow<Boolean?>(null)
    val bookRegistRes : StateFlow<Boolean?> = _bookRegistRes
    // 내 보유 책 리스트
    private val _myBookList = MutableStateFlow<List<Map<String,String>>>(emptyList())
    val myBookList = _myBookList
    fun changeState(){
        _reportState.value = !_reportState.value
    }
    fun resetRegistRes(){
        _reportRegistRes.value = null
        _bookRegistRes.value = null
    }
    fun bookSearch(isbn:String){
        GlobalScope.async{
            Log.d("start","start")
            try{
                bookRepository.bookSearch(isbn).collect(){
                        res -> _bookSearchRes.value = res
                        Log.d("vm",res.toString())
                        Log.d("rv",bookSearchRes.value.toString())
                }
            }catch (e:Exception){
                Log.d("fail","search fail")
                _bookSearchRes.value= mapOf("title" to "")
                Log.d("failres", bookSearchRes.value.toString())
            }
        }
    }
    fun bookReportRegist(reportInfo:BookReportReq){
        viewModelScope.async {
            Log.d("report API", "report API")
            try{
                bookRepository.reportRegist(reportInfo).collect(){
                    res -> _reportRegistRes.value = res
                    Log.d("reportregistres", _reportRegistRes.value.toString())
                }
            }catch (e:Exception){
                Log.d("report regist error", "독후감 등록 실패")
            }
        }
    }
    fun bookRegist(bookInfo:BookRegistReq){
        viewModelScope.async{
            Log.d("bookregist", "regist start")
            try{
                bookRepository.bookRegist(bookInfo).collect{
                    res -> _bookRegistRes.value = res
                }
            } catch(e:Exception){
                Log.d("bookregist res", "등록 오류")
            }
        }
    }
    fun getMyBookList(){
        viewModelScope.async {
            Log.d("get my book list", "get my book list")
            try{
                bookRepository.getMyBookList().collect(){
                    res-> _myBookList.value = res
                }
                Log.d("my book list", "my book list load success")
            }catch(e:Exception){
                Log.d("my book list"," my book list load fail")
            }
        }
    }
}
class BookViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            return BookViewModel() as T
        }
        throw IllegalArgumentException("unknown viewModel");
    }
}