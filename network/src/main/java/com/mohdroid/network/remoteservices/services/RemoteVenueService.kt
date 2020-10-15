package com.mohdroid.network.remoteservices.services

import com.mohdroid.domain.CLIENT_ID
import com.mohdroid.domain.CLIENT_SECRET
import com.mohdroid.domain.VERSION
import com.mohdroid.domain.dto.BaseResponse
import com.mohdroid.domain.dto.VenuRecomendationsResponse
import com.mohdroid.domain.dto.VenueDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Represents the Venue API call contract.
 *
 * @author Mohdroid
 */
interface RemoteVenueService {

    /**
     * Returns a list of recommended venues near the current location.
     * If authenticated, the method will personalize the ranking based on you and your friends.
     *
     * @param v Represents the “version” of the API for which you expect from Foursquare.
     * The value of the v parameter is a date in YYYYMMDD format that lets you tell us “I’m prepared for API changes up to this date.”
     * @param clientId for authorication
     * @param clientSecret for authorication
     * @param ll Latitude and longitude of the user’s location.
     * @param section One of food, drinks, coffee, shops, arts, outdoors, sights, trending, nextVenues
     * @param limit Number of results to return, up to 50.
     * @param offset Used to page through results, up to 50.
     *
     */
    @GET("venues/explore?")
    suspend fun getVenueRecommendations(
        @Query("v") v: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("ll") ll: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("section") section: String
    ): Response<VenuRecomendationsResponse>


    /**
     * Gives the full details about a venue.
     *
     * @param id Represents the venue id to query.
     */
    @GET("venues/{id}")
    suspend fun getVenueDetail(
        @Path("id") id: String,
        @Query("v") v: String = VERSION,
        @Query("client_id") clientId: String = CLIENT_ID,
        @Query("client_secret") clientSecret: String = CLIENT_SECRET
    ): Response<BaseResponse<VenueDetailResponse>>

}
