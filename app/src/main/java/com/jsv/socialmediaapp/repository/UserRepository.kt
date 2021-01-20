package com.jsv.socialmediaapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jsv.socialmediaapp.AppExecutors
import com.jsv.socialmediaapp.model.User
import com.jsv.socialmediaapp.request.LoginRequest
import com.jsv.socialmediaapp.response.LoginResponse
import com.jsv.socialmediaapp.service.ApiSuccessResponse
import com.jsv.socialmediaapp.service.ISessionManager
import com.jsv.socialmediaapp.service.SocialMediaService
import com.jsv.socialmediaapp.util.Constants
import com.jsv.socialmediaapp.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val ISocialMediaService: SocialMediaService,
    private val iSessionManager: ISessionManager
) {

    fun isLogin(): LiveData<User> {
        val isUser = ReadUserShaPrefTask(iSessionManager = iSessionManager)
        appExecutors.network().execute(isUser)
        return isUser.user
    }

    fun getUsers(loginRequest: LoginRequest): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, LoginResponse>(appExecutors) {

            override fun apiCall() = ISocialMediaService.login(loginRequest)

            override fun processResponse(response: ApiSuccessResponse<LoginResponse>)
                    : LoginResponse {
                return LoginResponse(response.body.status, response.body.data)
            }

            override fun saveCallResult(item: LoginResponse) {
              iSessionManager.saveData(Constants.USER_ID, item.data?.id)
              iSessionManager.saveData(Constants.USER_EMAIL, item.data?.email)
              iSessionManager.saveData(Constants.USER_NAME, item.data?.name)
              iSessionManager.saveData(Constants.USER_GITHUB_USERNAME, item.data?.githubUsername)
              iSessionManager.saveData(Constants.USER_REGISTERED_AT, item.data?.registeredAt)
              iSessionManager.saveData(Constants.USER_RATING, item.data?.rating)
            }

            override fun loadFromShaPref(): LiveData<User>  {
                val mutableList = MutableLiveData<User>()
                iSessionManager.fetchData<String>(Constants.USER_ID)?.let {userId->
                    iSessionManager.fetchData<String>(Constants.USER_EMAIL)?.let {email->
                        iSessionManager.fetchData<String>(Constants.USER_NAME)?.let {name->
                            iSessionManager.fetchData<String>(Constants.USER_GITHUB_USERNAME)?.let {gitHubName->
                               iSessionManager.fetchData<String>(Constants.USER_REGISTERED_AT)?.let {registerAt->
                                   iSessionManager.fetchData<String>(Constants.USER_RATING)?.let {rating->
                                      mutableList.value = User(userId,email,name,gitHubName,registerAt,rating)
                                   }
                               }
                            }
                        }
                    }
                }
                return mutableList
            }

        }.asLiveData()
    }
}
