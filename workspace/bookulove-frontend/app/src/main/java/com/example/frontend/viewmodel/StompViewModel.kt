package com.example.frontend.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.frontend.data.api.StompSingleton
import com.example.frontend.data.repository.PrefsRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompCommand
import ua.naiksoftware.stomp.dto.StompHeader
import ua.naiksoftware.stomp.dto.StompMessage

class StompViewModel() : ViewModel(){
    val intervalMillis = 1000L
    val client = OkHttpClient()
    val stompClient = StompSingleton.getStompInstance()
    fun runStomp(){
        val accessToken = PrefsRepository().getValue("accessToken")
        val headerList = arrayListOf<StompHeader>()
        headerList.add(StompHeader("Authorization","Bearer $accessToken"))
        stompClient.connect(headerList)

        stompClient.topic("/sub/2",headerList).subscribe(){
            Log.d("message receive", it.payload)
        }
        stompClient.lifecycle().subscribe { lifecycleEvent->
            when(lifecycleEvent.type){
                LifecycleEvent.Type.OPENED ->{
                    Log.d("OPEND", "!!")
                }
                LifecycleEvent.Type.CLOSED ->{
                    Log.d("closed"," !!")
                }
                LifecycleEvent.Type.ERROR -> {
                    Log.d("error", "connection error")
                }
                else ->{
                    Log.d("else circumstance", "else state")
                }
            }
        }
        stompClient.send(StompMessage(StompCommand.SEND,headerList, "asdfasdf")).subscribe()
    }
    fun disconnect(){
        stompClient.disconnect()
    }
}
