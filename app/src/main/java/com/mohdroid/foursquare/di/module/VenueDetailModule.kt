package com.mohdroid.foursquare.di.module

import com.mohdroid.foursquare.features.venuedetail.VenueDetailViewModel
import com.mohdroid.domain.service.VenueDetailService
import dagger.Module
import dagger.Provides

@Module
class VenueDetailModule {

    @Provides
    fun provideVenueDetailViewModel(
        venueDetailService: VenueDetailService
    ): VenueDetailViewModel =
        VenueDetailViewModel(
            venueDetailService
        )

    @Provides
    fun provideVenueDetailViewModelFactory(
        venueDetailService: VenueDetailService
    ): VenueDetailViewModel.Factory =
        VenueDetailViewModel.Factory(venueDetailService)

}