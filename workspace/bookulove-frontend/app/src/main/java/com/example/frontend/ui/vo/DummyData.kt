package com.example.frontend.ui.vo

data class chatList(
    val userName:String,
    val userChat:String,
)
val chatDataList = mutableListOf<chatList>(
    chatList(
        userName="asdf",
        userChat="hiasdfasdfasdfasdfadasdfdasfsdsf",
    ),
    chatList(
        userName="나",
        userChat="hi",
    ),
    chatList(
        userName="asdf",
        userChat="hiasdfsdafasdfdsfdasfadsfdsfsdafadsfasdfadsfasdfadsfdsaf",
    ),
    chatList(
        userName="나",
        userChat="hiasdfasdfasdfdasfasdfadsfsffsdfasdfadsfsdfafsdfasdfasdfsdfasdfasdfdsfasdfadsfadsfadsfdsafdsafadsfadsfdasfadsfdasfdasfdsfdsfadsfdsfadsfasdfds",
    ),
)