package com.example.frontend.data.repository

import android.util.Log
import com.example.frontend.data.api.API
import com.example.frontend.data.api.BookApi
import com.example.frontend.data.api.ChatApi
import com.example.frontend.data.model.ChatCreateReq
import com.example.frontend.data.model.ChatCreateRes
import com.example.frontend.data.model.ChattingRoomInfoDomainList
import com.example.frontend.data.model.EnterChatRoomData
import com.example.frontend.data.model.EnterChatRoomRes
import com.example.frontend.data.model.ExitChatRoomRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChatRepository(
    private val api:ChatApi = API.getInstance().create(ChatApi::class.java)
){
    suspend fun createChatRoom(seller:ChatCreateReq):Flow<Map<String,Int>> = flow{
        val response = api.createChatRoom(seller);
        if(response.code()==200){
            emit(response.body()!!.data)
        }else{
            emit(emptyMap())
        }
    }

    suspend fun getChatRoomList():Flow<List<ChattingRoomInfoDomainList>> = flow{
        Log.d("getchatroomlist"," suceess")
        val response = api.getChatRoomList()
        Log.d("getchatroomlist1111"," suceess")
        if(response.code()==200){
            Log.d("getchatroomlist"," suceess")
            emit(response.body()!!.data.chattingRoomInfoDomainList)
            Log.d("getchatroomlist"," suceess")
        }
        Log.d("getchatroomlist"," fail")
    }
    suspend fun exitChatRoom():Flow<Boolean> = flow{
        val response = api.exitChatRoom()
        if(response.code()==200){
            emit(true)
        }else{
            emit(false)
        }
    }
    suspend fun enterChatRoom(roomId:Int):Flow<EnterChatRoomData?> = flow{
        val response = api.enterChatRoom(roomId)
        if(response.code()==200){
            emit(response.body()!!.data)
        }else{
            emit(null)
        }
    }
}