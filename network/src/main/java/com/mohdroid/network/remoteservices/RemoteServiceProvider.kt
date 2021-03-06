package com.mohdroid.network.remoteservices

import com.mohdroid.network.remoteservices.services.RemoteVenueService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

interface RemoteServiceProvider {

    fun provideRemoteVenueService(): RemoteVenueService
}

@Singleton
class RemoteServiceProviderImpl @Inject constructor(
    private val retrofit: Retrofit
) : RemoteServiceProvider {

    override fun provideRemoteVenueService(): RemoteVenueService =
        retrofit.create(RemoteVenueService::class.java)

}