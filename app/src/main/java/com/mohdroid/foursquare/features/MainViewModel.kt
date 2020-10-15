package com.mohdroid.foursquare.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohdroid.foursquare.features.common.viewmodel.AbsViewModel

class MainViewModel: AbsViewModel() {


    class Factory(
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel() as T
        }

    }

}