package com.example.frontend.data.model

data class User(
    val id: String,
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
    val status: Int,
    val code: String,
    val data: Object,
)

data class PhoneNumber(
    val phoneNumber: String
)

data class Certification(
    val phoneNumber: String,
    val authCode: String
)

data class ModifyUser(
    var nickname: String,
    var libraryName: String,
    var lat: Double,
    var lng: Double
)

data class ModifyPw(
    var oldPassword: String,
    var newPassword: String
)

data class MapData(
    val status: Int,
    val code: String,
    val data: Map<String, Object>
)

data class LibraryList(
    val status: Int,
    val code: String,
    val data: Map<String, List<UserRegistDto>>
)