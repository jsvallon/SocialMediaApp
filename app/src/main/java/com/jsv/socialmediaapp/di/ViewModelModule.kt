package com.jsv.socialmediaapp.di

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jsv.socialmediaapp.view.SocialMediaViewModelFactory
import com.jsv.socialmediaapp.viewmodel.LoginFragmentViewModel
import com.jsv.socialmediaapp.viewmodel.NewsFeedFragmentViewModel
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
    @IntoMap
    @ViewModelKey(NewsFeedFragmentViewModel::class)
    abstract fun bindNewsFeedViewModel(userViewModel: NewsFeedFragmentViewModel): ViewModel



    @Binds
    abstract fun bindViewModelFactory(factory: SocialMediaViewModelFactory): ViewModelProvider.Factory
}