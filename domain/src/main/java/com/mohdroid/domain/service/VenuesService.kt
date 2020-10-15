package com.mohdroid.domain.service

import com.mohdroid.domain.dto.VenueRequestService
import com.mohdroid.domain.dto.VenueResponseService
import com.mohdroid.domain.network.common.BaseService
import com.mohdroid.domain.result.ServiceResult

interface VenuesService : BaseService {

    /**
     * @throws NullPointerException if the specified object is null and this
     *         service does not permit null elements
     */
    suspend fun getVenues(venueRequestService: VenueRequestService):
            ServiceResult<VenueResponseService>

}