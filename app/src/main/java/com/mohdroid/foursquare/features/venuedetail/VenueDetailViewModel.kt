package com.mohdroid.foursquare.features.venuedetail

import androidx.lifecycle.*
import com.mohdroid.foursquare.features.common.viewmodel.AbsViewModel
import com.mohdroid.domain.entity.VenueEntity
import com.mohdroid.domain.result.ServiceResult
import com.mohdroid.domain.service.VenueDetailService
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Inject tells Dagger how to create instance of VenueDetailViewModel
 */
class VenueDetailViewModel @Inject constructor(
    private val venueDetailService: VenueDetailService
) : AbsViewModel() {


    private val _venueId = MutableLiveData<String>()
    var venueId: String?
        get() = _venueId.value
        set(value) {
            _venueId.value = value
        }

    private val _showVenueDetail: MutableLiveData<VenueEntity> = MutableLiveData()
    val showVenueDetil: LiveData<VenueEntity>
        get() = _showVenueDetail

    init {
        viewModelScope.launch {
            showProgressAction()
            val result = venueDetailService.getVenueDetail(venueId!!)
            when (result) {
                is ServiceResult.Success<VenueEntity> -> {
                    hideProgressAction()
                    _showVenueDetail.postValue(result.data)
                }
                is ServiceResult.Failure -> handleError(result.error)
            }
        }
    }

    class Factory(
        private val venueDetailService: VenueDetailService
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return VenueDetailViewModel(
                venueDetailService
            ) as T
        }

    }


}