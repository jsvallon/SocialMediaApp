package com.jsv.socialmediaapp.service

import androidx.lifecycle.LiveData
import com.jsv.socialmediaapp.request.LoginRequest
import com.jsv.socialmediaapp.response.IResponse
import com.jsv.socialmediaapp.response.LoginResponse
import com.jsv.socialmediaapp.response.UserFeedResponse
import com.jsv.socialmediaapp.response.Wrapper
import com.jsv.socialmediaapp.util.Constants
import retrofit2.http.*



interface SocialMediaService {
    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginRequest): LiveData<ApiResponse<LoginResponse>>

//    @GET("users/{userId}/feed")
//    fun loadUsersFeed(@Path("userId") query: String): LiveData<ApiResponse<UserFeedResponse>>
}



interface SocialMediaServiceAdapter {
//    @POST(Constants.LOGIN_URL)
//    fun login(@Body request: LoginRequest): LiveData<ApiResponse<LoginResponse>>

    @GET("users/{userId}/feed")
    fun loadUsersFeed(@Path("userId") query: String): LiveData<ApiResponse<UserFeedResponse>>
}
