package com.mohdroid.service.di.module

import com.mohdroid.domain.service.UserLocationService
import com.mohdroid.domain.service.VenueDetailService
import com.mohdroid.domain.service.VenuesService
import com.mohdroid.service.UserLocationServiceServiceImpl
import com.mohdroid.service.VenueDetailServiceImpl
import com.mohdroid.service.VenueServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class ServiceModule {

    @Binds
    abstract fun provideAllVenuesListService(allVenueListServiceImpl: VenueServiceImpl): VenuesService

    @Binds
    abstract fun provideUserLocationService(userLocationServiceServiceImpl: UserLocationServiceServiceImpl): UserLocationService

    @Binds
    abstract fun provideVenueDetailService(venueDetailServiceImpl: VenueDetailServiceImpl): VenueDetailService

}