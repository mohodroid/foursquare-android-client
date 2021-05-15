package com.mohdroid.foursquare.features.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohdroid.foursquare.di.scope.ActivityScope
import com.mohdroid.foursquare.features.common.viewmodel.AbsViewModel
import com.mohdroid.service.SimpleAuthService

/**
 *  A unique instance of LoginViewModel is provided in Components
 *  annotated with @ActivityScope
 */
@ActivityScope
class LoginViewModel(
    private val authService: SimpleAuthService
) : AbsViewModel() {
    fun emailAddress(email: String): Boolean {
        authService.register(email)
        return true
    }


    class Factory(
        private val authService: SimpleAuthService
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(authService) as T
        }

    }
}