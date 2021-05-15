package com.mohdroid.service

import com.mohdroid.domain.common.convertToUpdateVenueEntity
import com.mohdroid.domain.dto.BaseResponse
import com.mohdroid.domain.dto.VenueDetailResponse
import com.mohdroid.domain.entity.VenueEntity
import com.mohdroid.domain.enums.ErrorType
import com.mohdroid.domain.network.VenueNetwork
import com.mohdroid.domain.repository.VenuesRepository
import com.mohdroid.domain.result.Error
import com.mohdroid.domain.result.ServiceResult
import com.mohdroid.domain.service.VenueDetailService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VenueDetailServiceImpl @Inject constructor(
    private val venueNetwork: VenueNetwork,
    private val venuesRepository: VenuesRepository
) : VenueDetailService {

    override suspend fun getVenueDetail(venueId: String): ServiceResult<VenueEntity> {
        if (venueId.isEmpty()) return ServiceResult.Failure(Error(ErrorType.EMPTY_VENUE_ID))
        var local: VenueEntity? = null
        withContext(IO) {
            local = venuesRepository.getVenueDetail(venueId)
        }
        local?.let {
            if (it.modified)
                return ServiceResult.Success(it)
        }

        val network = safeApiResult {
            venueNetwork.getVenueDetail(venueId)
        }
        when (network) {
            is ServiceResult.Success<BaseResponse<VenueDetailResponse>> -> {
                val venue = network.data?.response?.venue ?: return ServiceResult.Failure(
                    Error(
                        ErrorType.EXCEPTION
                    )
                )
                val updateVenue = venuesRepository.updateVenue(
                    local!!,
                    venue.convertToUpdateVenueEntity()
                )
                return ServiceResult.Success(updateVenue)
            }
            is ServiceResult.Failure -> return ServiceResult.Failure(network.error)
        }
    }
}