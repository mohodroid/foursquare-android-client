package com.mohdroid.domain.service

import com.mohdroid.domain.entity.UserLocationEntity

interface UserLocationService {

    suspend fun getCurrentLocation(): UserLocationEntity?

    suspend fun saveLocation(userLocationEntity: UserLocationEntity)


}