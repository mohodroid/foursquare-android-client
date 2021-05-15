package com.mohdroid.foursquare.features.venueslist

import android.location.Location
import androidx.lifecycle.*
import com.mohdroid.foursquare.features.common.Event
import com.mohdroid.foursquare.features.common.viewmodel.AbsViewModel
import com.mohdroid.domain.dto.VenueRequestService
import com.mohdroid.domain.dto.VenueResponseService
import com.mohdroid.domain.entity.VenueEntity
import com.mohdroid.domain.enums.ErrorType
import com.mohdroid.domain.enums.SectionType
import com.mohdroid.domain.result.ServiceResult
import com.mohdroid.domain.service.VenuesService
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mohdroid.foursquare.R


/**
 * @Inject tells Dagger how to create instance of VenuesListViewModel
 */
class VenuesListViewModel @Inject constructor(
    private val venuesService: VenuesService
) : AbsViewModel() {

    private var totalResult: Int = -1
    private var offset: Int = 0 //page index
    private val limit: Int = 10 // number items in each page

    private val _requestDeviceLocationPermission: MutableLiveData<Event<Unit>> = MutableLiveData()
    val requestDeviceLocationPermission: LiveData<Event<Unit>>
        get() = _requestDeviceLocationPermission

    private val _goToVenueDetailPage: MutableLiveData<Event<String>> = MutableLiveData()
    val goToVenueDetailPage: LiveData<Event<String>>
        get() = _goToVenueDetailPage

    private val data = MutableLiveData<Boolean>().apply {
        value = false
    }
    private var _venus: LiveData<List<VenueEntity>>? = null

    private val list: ArrayList<VenueEntity> = arrayListOf()

    val newVenues: LiveData<List<VenueEntity>> = Transformations.switchMap(data) {
        if (data.value!!) {
            _venus
        } else {
            MutableLiveData()
        }
    }

    fun onUserLocationReceived(location: Location) {
        getVenues(location)
    }

    fun onLoadMore(location: Location) {
        if (totalResult != 0) {
            val totalPage = totalResult / limit
            if (offset >= totalPage) return
        }
        offset += 1
        getVenues(location)
    }

    private fun getVenues(location: Location) {
        viewModelScope.launch {
            showProgressAction()
            val venueRequestService = VenueRequestService(
                location.latitude,
                location.longitude,
                SectionType.COFFEE,
                limit,
                offset * limit
            )
            val result = venuesService.getVenues(
                venueRequestService
            )
            when (result) {
                is ServiceResult.Success<VenueResponseService> -> {
                    hideProgressAction()
                    totalResult = result.data?.totalResults!!
                    _venus = result.data?.venues
                    data.postValue(true)
                    list.addAll(result.data?.venues?.value!!)
                }
                is ServiceResult.Failure -> {
                    hideProgressAction()
                    when (result.error.type) {
                        ErrorType.EMPTY_LATIDUDE ->
                            getShowErrorEvent().postValue(
                                Event(
                                    R.string.error_empty_location
                                )
                            )
                        ErrorType.EMPTY_LONGITUDE ->
                            getShowErrorEvent().postValue(
                                Event(
                                    R.string.error_empty_location
                                )
                            )
                        ErrorType.INVALID_LIMIT_NUMBER ->
                            getShowErrorEvent().postValue(
                                Event(
                                    R.string.error_general
                                )
                            )
                        ErrorType.INVALID_OFFSET_NUMBER ->
                            getShowErrorEvent().postValue(
                                Event(
                                    R.string.error_general
                                )
                            )
                        ErrorType.INVALID_SECTION_TYPE ->
                            getShowErrorEvent().postValue(
                                Event(
                                    R.string.error_general
                                )
                            )
                        else -> {
                            handleError(result.error)
                        }
                    }

                }
            }
        }
    }

    fun onVenueItemClicked(id: String) {
        _goToVenueDetailPage.value =
            Event(id)
    }

    class Factory(
        private val venuesService: VenuesService
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return VenuesListViewModel(
                venuesService
            ) as T
        }

    }


}