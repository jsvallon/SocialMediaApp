package com.jsv.socialmediaapp.di

import android.app.Application
import com.google.gson.GsonBuilder
import com.jsv.socialmediaapp.model.Activity
import com.jsv.socialmediaapp.response.UserFeedResponse
import com.jsv.socialmediaapp.service.ISessionManager
import com.jsv.socialmediaapp.service.SessionManagerImpl
import com.jsv.socialmediaapp.service.SocialMediaService
import com.jsv.socialmediaapp.service.SocialMediaServiceAdapter
import com.jsv.socialmediaapp.util.Constants
import com.jsv.socialmediaapp.util.DataDeserializer
import com.jsv.socialmediaapp.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {

    val percentDeserializer =
        GsonBuilder().registerTypeAdapter(Activity::class.java, DataDeserializer()).create()

    @Singleton
    @Provides
    fun provideSocialMediaService(): SocialMediaService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(SocialMediaService::class.java)
    }

    @Singleton
    @Provides
    fun provideSocialMediaServiceAdapter(): SocialMediaServiceAdapter {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

//            .addConverterFactory(GsonConverterFactory.create(percentDeserializer))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(SocialMediaServiceAdapter::class.java)
    }

    @Provides
    @Singleton
    fun provideSessionManager(application: Application) : ISessionManager {
        return SessionManagerImpl(application.applicationContext)
    }
}
