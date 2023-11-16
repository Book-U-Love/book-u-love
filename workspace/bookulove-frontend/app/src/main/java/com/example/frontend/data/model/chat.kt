package com.example.frontend.data.model


data class ChatCreateReq(
    val buId:Int,
    val sellerId:Int,
)
data class ChatCreateRes(
    val status:Number,
    val code:String,
    val data:Map<String,Int>
)
data class GetChatRoomListRes(
    val status:Number,
    val code:String,
    val data:GetChatRoomData
)
data class GetChatRoomData(
    val myId:Int,
    val chattingRoomInfoDomainList:List<ChattingRoomInfoDomainList>
)
data class ChattingRoomInfoDomainList(
    val roomId:Int,
    val buId:Int,
    val bookName:String,
    val targetId:String,
    val targetNickname:String,
    val lastContent:String,
    val lastTime:String,
    val unReadCount:Int,
)
data class ExitChatRoomRes(
    val status:Number,
    val code:String,
    val data:String,
)
data class EnterChatRoomRes(
    val status: Number,
    val code:String,
    val data: EnterChatRoomData
)
data class EnterChatRoomData(
    val roomId: Int,
    val sellerId: Int,
    val buyerId: Int,
    val buId: Int,
    val myId: Int,
    val targetName: String,
    val bookName: String,
    val chattingInfoDomainList: List<Map<String,String>>
)