package com.jsv.socialmediaapp.di

import android.content.SharedPreferences
import com.jsv.socialmediaapp.view.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): LoginFragment
}