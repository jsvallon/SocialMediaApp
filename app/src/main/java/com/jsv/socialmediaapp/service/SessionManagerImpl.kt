package com.jsv.socialmediaapp.service

import android.content.Context
import android.content.SharedPreferences
import com.jsv.socialmediaapp.R


class SessionManagerImpl constructor(context: Context) : ISessionManager{
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_NAME = "user_name"

    }

    /**
     * Function to save auth token
     */
    override fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    override fun saveUserName(name: String) {
        val editor = prefs.edit()
        editor.putString(USER_NAME, name)
        editor.apply()
    }


    /**
     * Function to fetch auth token
     */
    override fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    override fun fetchUsername(): String? {
        return prefs.getString(USER_NAME, null)
    }
}