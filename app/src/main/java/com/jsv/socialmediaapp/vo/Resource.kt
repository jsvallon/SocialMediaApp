package com.jsv.socialmediaapp.vo

import com.jsv.socialmediaapp.util.SocialApiStatus

data class Resource<out T>(val status: SocialApiStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SocialApiStatus.DONE, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(SocialApiStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(SocialApiStatus.LOADING, data, null)
        }
    }
}