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
import ua.naiksoftware.stomp.dto.StompHeader

class StompViewModel() : ViewModel(){
//    private lateinit var stompClient:StompClient
    val url = "wss://k9c209.p.ssafy.io/api/chatting-service/stomps"
    val intervalMillis = 1000L
    val client = OkHttpClient()

    val stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP,url)
    fun runStomp(){
        stompClient.topic("/sub/2").subscribe(){
            Log.d("message receive", it.payload)
        }
        val accessToken = PrefsRepository().getValue("accessToken")
        val headerList = arrayListOf<StompHeader>()
        headerList.add(StompHeader("Authorization","Bearer $accessToken"))
        stompClient.connect(headerList)

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
    }
//    private val messageSubject:Flowable<String> =
//        Flowable.create({
//            emitter ->
//                val disposable = stompClient.topic("/sub/2")
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({
//                        it->
//                        val message = it.payload
//                        Log.d("disposable res",message)
//                        emitter.onNext(message)
//                    },{ error ->
//                        emitter.onError(error)
//                    })
//            disposables.add(disposable)
//        }, BackpressureStrategy.BUFFER)
//
//    private val disposables = CompositeDisposable()
//
//    fun observeMessages(): Flowable<String>{
//        return messageSubject
//    }
//    fun connect() {
//        try{
//            stompClient.connect()
//        }catch(e:Exception){
//            Log.d("ㅅㅂ","ㅅㅂㅅ")
//        }
//
//    }
//
//    fun disconnect() {
//        stompClient.disconnect()
//        disposables.clear()
//    }
//
//    fun sendMessage(message: String) {
//        stompClient.send("/sub/2", message)
//    }
//
//    // StompClient 초기화 및 AccessToken 설정
//    fun init() {
//        StompSingleton.init()
//        stompClient = StompSingleton.getStompClient()
//    }

//    override fun onCleared() {
//        super.onCleared()
//        disposables.dispose()
//    }
}
