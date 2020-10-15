package com.mohdroid.domain.entity

import com.mohdroid.domain.dto.VenueBestPhoto

data class UpdatedVenueEntity(
    val description: String?,
    val rating: Float,
    val bestPhoto: VenueBestPhotoEntity
)