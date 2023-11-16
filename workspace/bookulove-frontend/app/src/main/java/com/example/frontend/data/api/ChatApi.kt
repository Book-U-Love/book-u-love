package com.example.frontend.data.api

import com.example.frontend.data.model.ChatCreateReq
import com.example.frontend.data.model.ChatCreateRes
import com.example.frontend.data.model.EnterChatRoomRes
import com.example.frontend.data.model.ExitChatRoomRes
import com.example.frontend.data.model.GetChatRoomData
import com.example.frontend.data.model.GetChatRoomListRes
import com.example.frontend.data.model.Token
import com.example.frontend.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatApi {
    @POST("chatting-service/chattings")
    suspend fun createChatRoom(@Body seller:ChatCreateReq):Response<ChatCreateRes>

    @GET("chatting-service/chattings")
    suspend fun getChatRoomList():Response<GetChatRoomListRes>

    @DELETE("chatting-service/chattings")
    suspend fun exitChatRoom():Response<ExitChatRoomRes>

    @GET("chatting-service/chattings/{roomId}")
    suspend fun enterChatRoom(@Path("roomId") roomId:Int):Response<EnterChatRoomRes>

}