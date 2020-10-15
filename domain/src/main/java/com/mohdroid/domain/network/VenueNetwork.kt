package com.mohdroid.domain.network

import com.mohdroid.domain.dto.BaseResponse
import com.mohdroid.domain.dto.VenuRecomendationsResponse
import com.mohdroid.domain.dto.VenueDetailResponse
import com.mohdroid.domain.dto.VenueRequest
import retrofit2.Response

/**
 * Contract to provides the Foursquare Venue APIs.
 *
 * @author Mohdroid
 */
interface VenueNetwork {

    suspend fun getVenues(venueRequest: VenueRequest): Response<VenuRecomendationsResponse>

    /**
     * Returns a venue detail.
     *
     * @param id Represents the venue id to query.
     */
    suspend fun getVenueDetail(id: String): Response<BaseResponse<VenueDetailResponse>>

}