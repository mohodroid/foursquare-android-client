package com.mohdroid.foursquare.di.module

import com.mohdroid.foursquare.features.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideMainViewModelFactory() = MainViewModel.Factory()

    @Provides
    fun provideMainViewModel() = MainViewModel()

}