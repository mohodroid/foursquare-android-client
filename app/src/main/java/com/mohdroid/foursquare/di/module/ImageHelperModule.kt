package com.mohdroid.foursquare.di.module

import com.mohdroid.foursquare.utils.LoadImageHelper
import com.mohdroid.foursquare.utils.LoadImageHelperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ImageHelperModule {

    @Binds
    abstract fun provideLoadingImageHelper(loadImageLoaderImpl: LoadImageHelperImpl): LoadImageHelper


}