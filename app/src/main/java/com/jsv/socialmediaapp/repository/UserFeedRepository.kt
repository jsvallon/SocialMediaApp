package com.jsv.socialmediaapp.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jsv.socialmediaapp.AppExecutors
import com.jsv.socialmediaapp.response.IResponse
import com.jsv.socialmediaapp.response.UserFeedResponse
import com.jsv.socialmediaapp.response.Wrapper
import com.jsv.socialmediaapp.service.ApiSuccessResponse
import com.jsv.socialmediaapp.service.SocialMediaService
import com.jsv.socialmediaapp.service.SocialMediaServiceAdapter
import com.jsv.socialmediaapp.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserFeedRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val ISocialMediaService: SocialMediaServiceAdapter
) {


    fun fetchFeeds(userId: String): LiveData<Resource<List<UserFeedResponse>>> {
        return object : NetworkBoundResourceWithWithOutSave<List<UserFeedResponse>, UserFeedResponse>(appExecutors) {

            override fun apiCall() = ISocialMediaService.loadUsersFeed(userId)

            override fun processResponse(response: ApiSuccessResponse<UserFeedResponse>): UserFeedResponse{
                val body = response.body
                //                return UserFeedResponse(response.body.status, response.body.dataComment, response.body.dataPost, response.body.dataGitHubEvent, response.body.pagination)
                return UserFeedResponse(response.body.status , response.body.pagination, response.body.dataPost)
            }

            override fun convertItem(item: UserFeedResponse): LiveData<List<UserFeedResponse>> {
                val mutableList = MutableLiveData<List<UserFeedResponse>>()
                mutableList.value = listOf(item)
                return mutableList
            }

        }.asLiveData()
    }
}
