package com.example.frontend.data.api

import com.example.frontend.data.model.Default
import com.example.frontend.data.model.LibraryList
import com.example.frontend.data.model.MapData
import com.example.frontend.data.model.ModifyPw
import com.example.frontend.data.model.ModifyUser
import com.example.frontend.data.model.ReviewList
import com.example.frontend.data.model.User
import com.example.frontend.data.model.UserRegistDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface UserApi {
    @POST("user-service/users")
    suspend fun signUp(@Body userInfo:UserRegistDto):Response<Default>

    @GET("user-service/users")
    suspend fun getMyInfo():Response<MapData>


    @GET("user-service/users/info")
    suspend fun getMyPage():Response<MapData>
    @GET("user-service/users/info/{userId}")
    suspend fun getUserPage(@Path("userId") userId: String): Response<MapData>

    @PATCH("user-service/users")
    suspend fun modifyUser(@Body modifyUser: ModifyUser):Response<Default>

    @PATCH("user-service/users/password")
    suspend fun modifyPassword(@Body modifyPw: ModifyPw): Response<Default>

    @GET("user-service/users/list")
    suspend fun getLibraryList(): Response<LibraryList>

    @GET("user-service/users/check/{userId}")
    suspend fun checkId(@Path("userId") userId: String): Response<Default>

    @GET("user-service/evals/reviewee")
    suspend fun getMyReviewList(): Response<ReviewList>

    @GET("user-service/evals/reviewee/{userId}")
    suspend fun getUserReviewList(@Path("userId") userId: String): Response<ReviewList>
}