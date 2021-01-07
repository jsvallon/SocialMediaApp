package com.jsv.socialmediaapp.di.session

import android.app.Application
import com.jsv.socialmediaapp.service.ISessionManager
import com.jsv.socialmediaapp.service.SessionManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class SessionManagerModule{
    @Provides
    @Singleton
    fun provideSessionManager(application: Application) : ISessionManager = SessionManagerImpl(application.applicationContext)
}
