package com.jsv.socialmediaapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jsv.socialmediaapp.view.SocialMediaViewModelFactory
import com.jsv.socialmediaapp.viewmodel.LoginFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginFragmentViewModel::class)
    abstract fun bindUserViewModel(userViewModel: LoginFragmentViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: SocialMediaViewModelFactory): ViewModelProvider.Factory
}