package com.mohdroid.service

import androidx.lifecycle.MutableLiveData
import com.mohdroid.domain.common.convertToVenueEntity
import com.mohdroid.domain.dto.*
import com.mohdroid.domain.entity.UserLocationEntity
import com.mohdroid.domain.entity.VenueEntity
import com.mohdroid.domain.enums.ErrorType
import com.mohdroid.domain.network.VenueNetwork
import com.mohdroid.domain.repository.VenuesRepository
import com.mohdroid.domain.result.Error
import com.mohdroid.domain.result.ServiceResult
import com.mohdroid.domain.result.ServiceResult.Failure
import com.mohdroid.domain.service.UserLocationService
import com.mohdroid.domain.service.VenuesService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin


@Singleton
class VenueServiceImpl @Inject constructor(
    private val venueNetwork: VenueNetwork,
    private val venuesRepository: VenuesRepository,
    private val userLocationService: UserLocationService
) : VenuesService {

    private val liveData: MutableLiveData<List<VenueEntity>> = MutableLiveData()

    override suspend fun getVenues(
        venueRequestService: VenueRequestService
    ): ServiceResult<VenueResponseService> {
        //all checked is here
        Objects.requireNonNull(venueRequestService)
        val validationResult = validateVenueRequest(venueRequestService)
        if (validationResult.first) return validationResult.second!!

        var currentLocation = userLocationService.getCurrentLocation()
        if (currentLocation == null) {
            currentLocation = UserLocationEntity(
                venueRequestService.latitude,
                venueRequestService.longitude
            )
            userLocationService.saveLocation(currentLocation)
        }

        val meterDistanceBetweenPoints = meterDistanceBetweenPoints(
            currentLocation.lat, currentLocation.lng,
            venueRequestService.latitude, venueRequestService.longitude
        )
        if (meterDistanceBetweenPoints >= 100) {
            return getNewLocationVenues(venueRequestService)
        }
        var local: List<VenueEntity>? = null
        var totalResult = 0
        withContext(IO) {
            totalResult = venuesRepository.getTotalCount()
            local =
                venuesRepository.getVenues(venueRequestService.limit, venueRequestService.offset)
            liveData.postValue(local)
        }
        if (!local.isNullOrEmpty())
            return ServiceResult.Success(VenueResponseService(0, liveData))

        val venueRequest = VenueRequest(
            section = venueRequestService.SectionType.value,
            ll = "${currentLocation.lat},${currentLocation.lng}",
            limit = venueRequestService.limit,
            offset = venueRequestService.offset
        )

        return callNetwork(venueRequest, totalResult)
    }

    private suspend fun getNewLocationVenues(venueRequestService: VenueRequestService): ServiceResult<VenueResponseService> {
        Objects.requireNonNull(venueRequestService)
        val validationResult = validateVenueRequest(venueRequestService)
        if (validationResult.first)
            return validationResult.second!!
        withContext(IO) {
            venuesRepository.removeOldVenues()
        }
        val venueRequest = VenueRequest(
            section = venueRequestService.SectionType.value,
            ll = "${venueRequestService.latitude},${venueRequestService.longitude}",
            limit = venueRequestService.limit,
            offset = venueRequestService.offset
        )
        return callNetwork(venueRequest, 0)
    }

    private suspend fun callNetwork(
        venueRequest: VenueRequest,
        totalResult: Int
    ): ServiceResult<VenueResponseService> {

        val network = safeApiResult {
            venueNetwork.getVenues(venueRequest)
        }
        when (network) {
            is ServiceResult.Success<VenuRecomendationsResponse> -> {
                val group: ArrayList<Group>? = network.data?.response?.groups
                if (group.isNullOrEmpty() || group[0].items.isNullOrEmpty()) {
                    val venueRecommendationResponseService = VenueResponseService(
                        totalResult,
                        liveData
                    )
                    return ServiceResult.Success(venueRecommendationResponseService)
                }
                val venues = network.data?.response?.groups?.get(0)?.items!!.map {
                    it.venue.convertToVenueEntity()
                }
                withContext(IO) {
                    venuesRepository.insertVenues(venues)
                }
                liveData.postValue(venues)
                //prevent non-null
                val venueRecommendationResponseService = VenueResponseService(
                    network.data?.response?.totalResults!!,
                    liveData
                )
                return ServiceResult.Success(venueRecommendationResponseService)
            }
            is Failure -> {
                return Failure(network.error)
            }
        }
    }

    private fun validateVenueRequest(venueRequestService: VenueRequestService): Pair<Boolean, Failure?> {
        var failure: Failure? = null
        val latitude = venueRequestService.latitude
        if (latitude <= 0.0)
            failure = Failure(Error(ErrorType.EMPTY_LATIDUDE))
        val longitude = venueRequestService.longitude
        if (longitude <= 0.0)
            failure = Failure(Error(ErrorType.EMPTY_LONGITUDE))
        val section = venueRequestService.SectionType.value
        if (section.isEmpty())
            failure = Failure(Error(ErrorType.INVALID_SECTION_TYPE))
        val limit = venueRequestService.limit
        if (limit > 50)
            failure = Failure(Error(ErrorType.INVALID_LIMIT_NUMBER))
        val offset = venueRequestService.offset
        if (offset > 50)
            failure = Failure(Error(ErrorType.INVALID_OFFSET_NUMBER))
        failure?.let {
            return Pair(true, it)
        }
        return Pair(false, failure)
    }

    private fun meterDistanceBetweenPoints(
        lat_a: Double,
        lng_a: Double,
        lat_b: Double,
        lng_b: Double
    ): Double {
        val pk = (180f / Math.PI).toFloat()
        val a1 = lat_a / pk
        val a2 = lng_a / pk
        val b1 = lat_b / pk
        val b2 = lng_b / pk
        val t1 =
            cos(a1) * cos(a2) * cos(b1) * cos(b2)
        val t2 =
            cos(a1) * sin(a2) * cos(b1) * sin(b2)
        val t3 = sin(a1) * sin(b1)
        val tt = acos(t1 + t2 + t3)
        return 6366000 * tt
    }

}

