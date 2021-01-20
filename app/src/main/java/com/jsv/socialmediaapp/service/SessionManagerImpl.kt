package com.jsv.socialmediaapp.service

import android.content.Context
import android.content.SharedPreferences
import com.jsv.socialmediaapp.R




class SessionManagerImpl constructor(context: Context) : ISessionManager {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    /**
     * Function to save data
     */
    override fun <T> saveData(key: String, value: T) {
        val editor = prefs.edit()
        editor.putString(key, value.toString())
        editor.apply()
    }

    /**
     * Function to fetch data
     */
    override fun <T> fetchData(key: String): T?{
      return prefs.getString(key.toString(), null) as T
    }
}