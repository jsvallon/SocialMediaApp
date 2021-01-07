package com.jsv.socialmediaapp.service

interface ISessionManager {
    fun <T> saveData(key: String, value:T)
    fun <T> fetchData(key: String) : T?
}




