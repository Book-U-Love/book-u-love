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
import com.example.frontend.data.repository.BookRepository
import kotlinx.coroutines.async
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

    fun changeState(){
        _reportState.value = !_reportState.value
    }
    fun changeExpandState(){
        _isExpanded.value = !_isExpanded.value
    }
    fun bookSearch(isbn:String){
        viewModelScope.async{
            Log.d("start","start")
            try{
                bookRepository.bookSearch(isbn).collect(){
                        res ->

                }
            }catch (e:Exception){
                Log.d("fail","search fail")
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