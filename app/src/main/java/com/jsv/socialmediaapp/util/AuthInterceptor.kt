package com.jsv.socialmediaapp.util

import android.content.Context
import com.jsv.socialmediaapp.service.SessionManagerImpl
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {
    //private val sessionManager = SessionManagerImpl()
    private val sessionManager = SessionManagerImpl(context)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}