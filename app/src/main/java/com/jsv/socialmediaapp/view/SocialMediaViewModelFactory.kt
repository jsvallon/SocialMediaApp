package com.jsv.socialmediaapp.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jsv.socialmediaapp.viewmodel.LoginFragmentViewModel
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


@Singleton
class SocialMediaViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        @Suppress("UNCHECKED_CAST")
        return creator.get() as T
    }
}


//
//class SocialMediaViewModelFactory(
//    private val application: Application
//) : ViewModelProvider.Factory {
//
//    @Suppress("unchecked_cast")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(LoginFragmentViewModel::class.java)) {
//            return LoginFragmentViewModel(
//                application
//            ) as T
//        }
//        if (modelClass.isAssignableFrom(LoginFragmentViewModel::class.java)) {
//            return LoginFragmentViewModel(application) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//
//}
//
