package com.mohdroid.domain.dto

import androidx.lifecycle.LiveData
import com.mohdroid.domain.entity.VenueEntity

data class VenueResponseService(
    val totalResults: Int,
    val venues: LiveData<List<VenueEntity>>?
)