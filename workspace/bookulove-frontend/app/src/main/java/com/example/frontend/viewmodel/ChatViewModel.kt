package com.example.frontend.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.frontend.data.model.ChatCreateReq
import com.example.frontend.data.model.ChattingRoomInfoDomainList
import com.example.frontend.data.model.EnterChatRoomData
import com.example.frontend.data.model.GetChatRoomData
import com.example.frontend.data.model.GetChatRoomListRes
import com.example.frontend.data.repository.ChatRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed


class ChatViewModel(): ViewModel(){
    private val chatRepository: ChatRepository = ChatRepository()
    // 생성된 방의 정보
    private val _createdRoom: MutableState<Map<String,Int>> = mutableStateOf(emptyMap())
    val createdRoom:State<Map<String,Int>> = _createdRoom
    // 채팅방 목록
    private val _chatRoomList: MutableState<List<ChattingRoomInfoDomainList>?> = mutableStateOf(
        null
    )
    val chatRoomList:State<List<ChattingRoomInfoDomainList>?> = _chatRoomList
    // 퇴장 채팅방 정보
    private val _exitChatRoom: MutableState<Boolean?> = mutableStateOf(null)
    val exitChatRoom:State<Boolean?> = _exitChatRoom
    // 입장 채팅방 정보
    private val _enterChatRoom:MutableState<EnterChatRoomData?> = mutableStateOf(null)
    val enterChatRoom:State<EnterChatRoomData?> = _enterChatRoom


    fun createChatRoom(seller:ChatCreateReq){
        viewModelScope.async{
            try{
                chatRepository.createChatRoom(seller).collect(){
                    res-> _createdRoom.value = res
                }
            }catch (e:Exception){

            }
        }
    }
    fun getChatRoomList(){
        viewModelScope.async {
            try{
                chatRepository.getChatRoomList().collect(){
                    res-> _chatRoomList.value = res
                    Log.d("chatroomlist res", "success")
                }
            }catch (e:Exception){
                Log.d("chatroomlist res", "fail;")
            }
        }
    }
    fun exitChatRoom(){
        viewModelScope.async {
            try{
                chatRepository.exitChatRoom().collect(){

                }
            }catch (e:Exception){

            }
        }
    }
    fun enterChatRoom(roomId:Int){
        viewModelScope.async {
            try{
                chatRepository.enterChatRoom(roomId).collect(){

                }
            }catch (e:Exception){

            }
        }
    }
}

class ChatViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel() as T
        }
        throw IllegalArgumentException("unknown viewModel");
    }
}