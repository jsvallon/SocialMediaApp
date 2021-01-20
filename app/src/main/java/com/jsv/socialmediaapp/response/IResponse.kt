package com.jsv.socialmediaapp.response

import com.google.gson.Gson

interface IResponse {
    fun toJSON(): String = Gson().toJson(this)
}
inline fun <reified T: IResponse> String.toObject(): T = Gson().fromJson(this, T::class.java)

