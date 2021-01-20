package com.jsv.socialmediaapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.jsv.socialmediaapp.model.Post
import com.jsv.socialmediaapp.repository.UserFeedRepository
import com.jsv.socialmediaapp.response.UserFeedResponse
import com.jsv.socialmediaapp.util.AbsentLiveData
import com.jsv.socialmediaapp.util.Constants
import com.jsv.socialmediaapp.util.SocialApiStatus
import com.jsv.socialmediaapp.util.formatData
import com.jsv.socialmediaapp.vo.Resource
import java.util.*

import javax.inject.Inject

class NewsFeedFragmentViewModel
@Inject constructor(private val userFeedRepository: UserFeedRepository, application: Application) : AndroidViewModel(application) {


    private val _userId = MutableLiveData<String?>()
    val login: LiveData<String?>
        get() = _userId

    private val _userNewsFeed: LiveData<Resource<List<UserFeedResponse>>> = _userId.switchMap { userId ->
        if (userId == null) {
            AbsentLiveData.create()
        } else {
            userFeedRepository.fetchFeeds(userId)
        }
    }

    val userNewsFeed: LiveData<Resource<List<UserFeedResponse>>>
    get() = _userNewsFeed

    val loading: LiveData<SocialApiStatus>
        get() = Transformations.map(_userNewsFeed) { it-> return@map it.status }

    val userPost = Transformations.map(_userNewsFeed) {
        formatData(it.data,Constants.NEW_POST)
    }

    fun setUserId(userId: String) {
        if (_userId.value != userId) {
            _userId.value = userId
        }
    }

}

/*
*
* class LoginFragmentViewModel
@Inject constructor(private val userRepository: UserRepository, application: Application) : AndroidViewModel(application) {

    private val _login = MutableLiveData<String?>()
    val login: LiveData<String?>
        get() = _login

    private val _loginRequest = MutableLiveData<LoginRequest>()
    val loginRequest: LiveData<LoginRequest>
        get() = _loginRequest

    val user: LiveData<Resource<User>> = _loginRequest.switchMap { login ->
        if (login == null) {
            AbsentLiveData.create()
        } else {
            userRepository.getUsers(LoginRequest(email = login.email, password = login.password))
        }
    }

    val isLogin: LiveData<User> = userRepository.isLogin()


    fun setLogin(email: String, password: String) {
      if (validEmailAndPassword(email.toLowerCase(Locale.getDefault()).trim(), password.toLowerCase(Locale.getDefault()).trim()) ) {
          val loginRequest = LoginRequest(email, password)
          if (_loginRequest.value != loginRequest) {
              _loginRequest.value = loginRequest
          }
      }
    }


    private fun validEmailAndPassword(email: String, password: String): Boolean {
      if (email.isNullOrBlank()) {
          return false
      }
      if (password.isNullOrBlank()) {
         return false
      }
      return true
    }
}

*
*
* */