package com.jsv.socialmediaapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.jsv.socialmediaapp.model.User
import com.jsv.socialmediaapp.repository.UserRepository
import com.jsv.socialmediaapp.request.LoginRequest
import com.jsv.socialmediaapp.util.AbsentLiveData
import com.jsv.socialmediaapp.vo.Resource
import java.util.*
import javax.inject.Inject


class LoginFragmentViewModel
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
