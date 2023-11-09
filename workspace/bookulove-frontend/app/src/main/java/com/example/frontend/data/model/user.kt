package com.example.frontend.data.model

data class User(
    val loginId: String,
    val password: String,
)
data class UserRegistDto(
    var id: String,
    var password: String,
    var phoneNumber: String,
    var nickname: String,
    var libraryName: String,
    var lat: Double,
    var lng: Double,
)
data class Default(
    val status: Number,
    val code: String,
    val data: String,
)