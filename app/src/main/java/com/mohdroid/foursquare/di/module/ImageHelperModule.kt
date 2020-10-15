package com.mohdroid.foursquare.di.module

import com.mohdroid.foursquare.utils.LoadImageHelper
import com.mohdroid.foursquare.utils.LoadImageHelperImpl
import dagger.Module
import dagger.Provides

@Module
class ImageHelperModule {

    @Provides
    fun provideLoadingImageHelper(
        loadImageLoaderImpl: LoadImageHelperImpl
    ): LoadImageHelper = loadImageLoaderImpl


}