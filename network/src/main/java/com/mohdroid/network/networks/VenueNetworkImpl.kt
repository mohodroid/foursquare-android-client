package com.mohdroid.network.networks

import com.mohdroid.domain.dto.BaseResponse
import com.mohdroid.domain.dto.VenuRecomendationsResponse
import com.mohdroid.domain.dto.VenueDetailResponse
import com.mohdroid.domain.dto.VenueRequest
import com.mohdroid.domain.network.VenueNetwork
import com.mohdroid.network.remoteservices.RemoteServiceProvider
import retrofit2.Response
import javax.inject.Inject

/**
 * An implementation of [VenueNetwork].
 *
 * @author Mohdroid
 */
class VenueNetworkImpl @Inject constructor(
    private val remoteServiceProvider: RemoteServiceProvider
) : VenueNetwork {

    override suspend fun getVenues(venueRequest: VenueRequest): Response<VenuRecomendationsResponse> {
        return remoteServiceProvider.provideRemoteVenueService()
            .getVenueRecommendations(
                venueRequest.version,
                venueRequest.clientId,
                venueRequest.clientSecret,
                venueRequest.ll,
                offset = venueRequest.offset,
                limit = venueRequest.limit,
                section = venueRequest.section
            )
    }

    override suspend fun getVenueDetail(id: String): Response<BaseResponse<VenueDetailResponse>> {
        return remoteServiceProvider.provideRemoteVenueService().getVenueDetail(id)
    }
}
