package com.jsv.socialmediaapp.service

import androidx.lifecycle.LiveData
import com.jsv.socialmediaapp.request.LoginRequest
import com.jsv.socialmediaapp.response.LoginResponse
import com.jsv.socialmediaapp.util.Constants
import retrofit2.http.*



interface SocialMediaService {
    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginRequest): LiveData<ApiResponse<LoginResponse>>
}
