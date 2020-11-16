package com.mohdroid.foursquare.features.common.viewmodel

import androidx.lifecycle.LifecycleObserver
import com.mohdroid.foursquare.features.common.Event
import com.mohdroid.domain.enums.ErrorType.*
import com.mohdroid.domain.result.Error
import com.mohdroid.foursquare.R

interface BaseViewModel : LifecycleObserver,
    ProgressViewModel,
    ErrorViewModel {
    fun handleError(error: Error) {
        hideProgressAction()
        when (error.type) {
            IO_EXCEPTION -> getShowNoNetworkErrorEvent().postValue(
                Event(
                    R.string.no_network_error
                )
            )
            JSON_SYNTAX_EXCEPTION -> getShowErrorEvent().postValue(
                Event(
                    R.string.str_deserialization_error
                )
            )
            HTTP_EXCEPTION -> getShowErrorEvent().postValue(
                Event(
                    R.string.general_server_error
                )
            )
            EOF_EXCEPTION -> getShowErrorEvent().postValue(
                Event(
                    R.string.str_deserialization_error
                )
            )
            EXCEPTION -> getShowErrorEvent().postValue(
                Event(
                    R.string.error_general
                )
            )
            JSON_EXCEPTION -> getShowErrorEvent().postValue(
                Event(
                    R.string.str_deserialization_error
                )
            )
        }
    }
}