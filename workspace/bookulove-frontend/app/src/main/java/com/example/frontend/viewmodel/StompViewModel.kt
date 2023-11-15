package com.example.frontend.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.frontend.data.api.StompSingleton
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ua.naiksoftware.stomp.StompClient

class StompViewModel() : ViewModel(){
    private lateinit var stompClient:StompClient
    private val messageSubject:Flowable<String> =
        Flowable.create({
            emitter ->
                val disposable = stompClient.topic("/sub/2")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        it->
                        val message = it.payload
                        Log.d("disposable res",message)
                        emitter.onNext(message)
                    },{ error ->
                        emitter.onError(error)
                    })
            disposables.add(disposable)
        }, BackpressureStrategy.BUFFER)

    private val disposables = CompositeDisposable()

    fun observeMessages(): Flowable<String>{
        return messageSubject
    }
    fun connect() {
        try{
            stompClient.connect()
        }catch(e:Exception){
            Log.d("ㅅㅂ","ㅅㅂㅅ")
        }
        
    }

    fun disconnect() {
        stompClient.disconnect()
        disposables.clear()
    }

    fun sendMessage(message: String) {
        stompClient.send("/sub/2", message)
    }

    // StompClient 초기화 및 AccessToken 설정
    fun init() {
        StompSingleton.init()
        stompClient = StompSingleton.getStompClient()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
