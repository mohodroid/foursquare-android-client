package com.mohdroid.domain.persistent

import com.mohdroid.domain.entity.UserLocationEntity

interface UserLocationPersistent {

    fun insertCurrentLocation(userLocationEntity: UserLocationEntity)

    fun getCurrentLocation(): UserLocationEntity

    fun delete()

}