package com.mohdroid.foursquare.features.common.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mohdroid.foursquare.features.common.Event


interface ErrorViewModel {
    fun getShowNoNetworkErrorEvent(): MutableLiveData<Event<Int>>
    fun getShowErrorEvent(): MutableLiveData<Event<Int>>
    fun getShowWarningEvent(): MutableLiveData<Event<String>>
}
