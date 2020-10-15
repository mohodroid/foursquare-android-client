package com.mohdroid.service.di

import com.mohdroid.domain.service.UserLocationService
import com.mohdroid.domain.service.VenueDetailService
import com.mohdroid.domain.service.VenuesService
import com.mohdroid.service.UserLocationServiceServiceImpl
import com.mohdroid.service.VenueDetailServiceImpl
import com.mohdroid.service.VenueServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServiceModule {

    @Singleton
    @Provides
    fun provideAllVenuesListService(allVenueListServiceImpl: VenueServiceImpl): VenuesService =
        allVenueListServiceImpl

    @Singleton
    @Provides
    fun provideUserLocationService(userLocationServiceServiceImpl: UserLocationServiceServiceImpl)
            : UserLocationService =
        userLocationServiceServiceImpl

    @Singleton
    @Provides
    fun provideVenueDetailService(venueDetailServiceImpl: VenueDetailServiceImpl): VenueDetailService =
        venueDetailServiceImpl


}