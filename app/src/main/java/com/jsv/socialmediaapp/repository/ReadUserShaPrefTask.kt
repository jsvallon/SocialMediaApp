package com.jsv.socialmediaapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jsv.socialmediaapp.model.User
import com.jsv.socialmediaapp.service.ISessionManager
import com.jsv.socialmediaapp.util.Constants

class ReadUserShaPrefTask constructor(
    private val iSessionManager: ISessionManager
) : Runnable {
    private val _user = MutableLiveData<User>()
    val user : LiveData<User>
    get() = _user

    override fun run() {
        iSessionManager.fetchData<String>(Constants.USER_ID)?.let { userId->
            iSessionManager.fetchData<String>(Constants.USER_EMAIL)?.let { email->
                iSessionManager.fetchData<String>(Constants.USER_NAME)?.let { name->
                    iSessionManager.fetchData<String>(Constants.USER_GITHUB_USERNAME)?.let { gitHubName->
                        iSessionManager.fetchData<String>(Constants.USER_REGISTERED_AT)?.let { registerAt->
                            iSessionManager.fetchData<String>(Constants.USER_RATING)?.let { rating->
                                _user.postValue(User(userId,email,name,gitHubName,registerAt,rating))
                            }
                        }
                    }
                }
            }
        }
    }
}