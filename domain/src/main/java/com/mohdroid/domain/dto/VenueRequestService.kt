package com.mohdroid.domain.dto

import com.mohdroid.domain.enums.SectionType

data class VenueRequestService(
    val latitude: Double,
    val longitude: Double,
    val SectionType: SectionType,
    val limit: Int,
    val offset: Int
)