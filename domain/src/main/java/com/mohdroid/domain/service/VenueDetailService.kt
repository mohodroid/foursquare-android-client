package com.mohdroid.domain.service

import com.mohdroid.domain.entity.VenueEntity
import com.mohdroid.domain.network.common.BaseService
import com.mohdroid.domain.result.ServiceResult

interface VenueDetailService : BaseService {

    suspend fun getVenueDetail(venueId: String): ServiceResult<VenueEntity>

}