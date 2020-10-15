package com.mohdroid.foursquare.features.common.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohdroid.foursquare.features.common.Event

abstract class AbsViewModel : ViewModel(),
    BaseViewModel {
    /**
     * General
     */
    private val showProgressEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    private val hideProgressEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    private val showErrorEvent: MutableLiveData<Event<Int>> = MutableLiveData()
    private val showNoNetworkEvent: MutableLiveData<Event<Int>> = MutableLiveData()
    private val showWarningEvent: MutableLiveData<Event<String>> = MutableLiveData()

    override fun getHideProgressEvent(): MutableLiveData<Event<Unit>> = hideProgressEvent

    override fun getShowProgressEvent(): MutableLiveData<Event<Unit>> = showProgressEvent

    override fun getShowErrorEvent(): MutableLiveData<Event<Int>> = showErrorEvent

    override fun getShowNoNetworkErrorEvent(): MutableLiveData<Event<Int>> = showNoNetworkEvent

    override fun getShowWarningEvent(): MutableLiveData<Event<String>> = showWarningEvent

}