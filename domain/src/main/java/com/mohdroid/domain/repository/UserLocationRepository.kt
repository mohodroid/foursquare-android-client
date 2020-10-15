package com.mohdroid.domain.repository

import com.mohdroid.domain.dto.Location
import com.mohdroid.domain.entity.UserLocationEntity

interface UserLocationRepository {

    fun insertCurrentLocation(userLocationEntity: UserLocationEntity)

    fun getCurrentLocation(): UserLocationEntity

    fun delete()

}