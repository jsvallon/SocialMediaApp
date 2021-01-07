package com.jsv.socialmediaapp.service

interface ISessionManager {
    fun saveAuthToken(token: String)
    fun saveUserName(name: String)
    fun fetchAuthToken(): String?
    fun fetchUsername(): String?
}
