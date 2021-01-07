package com.jsv.socialmediaapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jsv.socialmediaapp.AppExecutors
import com.jsv.socialmediaapp.model.User
import com.jsv.socialmediaapp.request.LoginRequest
import com.jsv.socialmediaapp.response.LoginResponse
import com.jsv.socialmediaapp.service.ApiSuccessResponse
import com.jsv.socialmediaapp.service.SocialMediaService
import com.jsv.socialmediaapp.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val ISocialMediaService: SocialMediaService
) {

    fun getUsers(loginRequest: LoginRequest): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, LoginResponse>(appExecutors) {

            override fun apiCall() = ISocialMediaService.login(loginRequest)

            override fun convertItem(item: LoginResponse): LiveData<User> {
                val mutableList = MutableLiveData<User>()
                mutableList.value = item.data
                return mutableList
            }

            override fun processResponse(response: ApiSuccessResponse<LoginResponse>)
                    : LoginResponse {
                return LoginResponse(response.body.status, response.body.data)
            }
        }.asLiveData()
    }
}
