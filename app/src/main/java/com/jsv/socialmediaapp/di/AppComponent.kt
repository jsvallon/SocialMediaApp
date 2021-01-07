package com.jsv.socialmediaapp.di

import android.app.Application
import com.jsv.socialmediaapp.SocialMediaApp
import com.jsv.socialmediaapp.di.session.SessionManagerModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class,
        SessionManagerModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(stackOverFlowApp: SocialMediaApp)
}
