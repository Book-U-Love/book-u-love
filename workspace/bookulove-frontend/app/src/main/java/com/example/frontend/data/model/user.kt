package com.example.frontend.data.model

data class User(
    val loginId: String,
    val password: String,
)
data class UserRegistDto(
    val id: String,
    val password: String,
    val nickname: String,
    val libraryName: String,
    val lat: Double,
    val lng: Double,
)
data class Default(
    val status: Number,
    val code: String,
    val data: String,
)