package com.example.frontend.viewmodel

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.frontend.data.model.BookReportReq
import com.example.frontend.data.model.BookSearchRes
import com.example.frontend.data.repository.BookRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    fun changeState(){
        _reportState.value = !_reportState.value
    }
    fun resetRegistRes(){
        _reportRegistRes.value = null
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
}
class BookViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            return BookViewModel() as T
        }
        throw IllegalArgumentException("unknown viewModel");
    }
}