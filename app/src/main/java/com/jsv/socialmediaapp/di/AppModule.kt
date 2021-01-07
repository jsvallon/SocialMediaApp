package com.jsv.socialmediaapp.di

import android.content.Context
import android.content.SharedPreferences
import com.jsv.socialmediaapp.service.SocialMediaService
import com.jsv.socialmediaapp.util.Constants
import com.jsv.socialmediaapp.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideGithubService(): SocialMediaService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(SocialMediaService::class.java)
    }
}
