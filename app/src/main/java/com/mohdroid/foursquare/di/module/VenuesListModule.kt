package com.mohdroid.foursquare.di.module

import com.mohdroid.foursquare.features.venueslist.VenuesListViewModel
import com.mohdroid.domain.service.VenuesService
import dagger.Module
import dagger.Provides

@Module
class VenuesListModule {

    @Provides
    fun provideVenuesListViewModel(
        venuesService: VenuesService
    ): VenuesListViewModel =
        VenuesListViewModel(
            venuesService
        )

    @Provides
    fun provideVenuesListViewModelFactory(
        venuesService: VenuesService
    ): VenuesListViewModel.Factory = VenuesListViewModel.Factory(venuesService)

}