package com.mohdroid.foursquare.di.component

import com.mohdroid.foursquare.di.module.*
import com.mohdroid.foursquare.features.MainActivity
import com.mohdroid.foursquare.features.venuedetail.VenueDetailFragment
import com.mohdroid.foursquare.features.venueslist.VenuesListFragment
import com.mohdroid.network.di.module.NetworkModule
import com.mohdroid.repository.di.module.RepositoryModule
import com.mohdroid.service.di.ServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ServiceModule::class,
        MainModule::class,
        AppModule::class,
        VenuesListModule::class,
        RepositoryModule::class,
        VenueDetailModule::class,
        ImageHelperModule::class
    ]
)

interface AppComponent {

    fun inject(venuesListFragment: VenuesListFragment)

    fun inject(mainActivity: MainActivity)

    fun inject(venueDetailFragment: VenueDetailFragment)

}