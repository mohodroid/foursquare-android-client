package com.mohdroid.domain.entity

import androidx.room.Entity


data class VenueLocationEntity(
    val address: String?,
    val city: String?,
    val lat: Double,
    val lng: Double
)