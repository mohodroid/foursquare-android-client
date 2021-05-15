package com.mohdroid.foursquare.di.component

import com.mohdroid.foursquare.di.module.*
import com.mohdroid.foursquare.features.MainActivity
import com.mohdroid.foursquare.features.venuedetail.VenueDetailFragment
import com.mohdroid.foursquare.features.venueslist.VenuesListFragment
import com.mohdroid.network.di.module.NetworkModule
import com.mohdroid.repository.di.module.RepositoryModule
import com.mohdroid.service.di.module.ServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ServiceModule::class,
        NetworkModule::class,
        MainModule::class,
        AppModule::class,
        VenuesListModule::class,
        RepositoryModule::class,
        VenueDetailModule::class,
        ImageHelperModule::class
    ]
)

interface AppGraph {

    // This tells Dagger that VenuesListFragment requests injection so the graph needs to
    // satisfy all the dependencies of the fields that VenuesListFragment is requesting.(venueListVM) and its own dependencies
    fun inject(venuesListFragment: VenuesListFragment)

    fun inject(mainActivity: MainActivity)

    fun inject(venueDetailFragment: VenueDetailFragment)

}