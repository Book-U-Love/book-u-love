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
    chatList(
        userName="asdf",
        userChat="hiasdfsdafasdfdsfdasfadsfdsfsdafadsfasdfadsfasdfadsfdsaf",
    ),
    chatList(
        userName="asdf",
        userChat="hiasdfsdafasdfdsfdasfadsfdsfsdafadsfasdfadsfasdfadsfdsaf",
    ),
    chatList(
        userName="asdf",
        userChat="hiasdfsdafasdfdsfdasfadsfdsfsdafadsfasdfadsfasdfadsfdsaf",
    ),
    chatList(
        userName="asdf",
        userChat="hiasdfsdafasdfdsfdasfadsfdsfsdafadsfasdfadsfasdfadsfdsaf",
    ),
)

val categoryList = mutableListOf<String>(
    "독후감",
    "판매",
    "대여",
    "내 주변"
    
)
