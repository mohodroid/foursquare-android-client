package com.mohdroid.foursquare.di.module

import com.mohdroid.foursquare.features.auth.LoginViewModel
import com.mohdroid.service.SimpleAuthService
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun provideLoginViewModelFactory(authService: SimpleAuthService): LoginViewModel.Factory = LoginViewModel.Factory(authService)
}