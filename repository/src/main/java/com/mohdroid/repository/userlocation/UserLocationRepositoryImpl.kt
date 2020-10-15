package com.mohdroid.repository.userlocation

import com.mohdroid.domain.dto.Location
import com.mohdroid.domain.entity.UserLocationEntity
import com.mohdroid.domain.repository.UserLocationRepository
import javax.inject.Inject

class UserLocationRepositoryImpl @Inject constructor(
    private val userLocationDao: UserLocationDao
): UserLocationRepository {

    override fun insertCurrentLocation(userLocationEntity: UserLocationEntity) {
        userLocationDao.insertCurrentLocation(userLocationEntity)
    }

    override fun getCurrentLocation(): UserLocationEntity {
        return userLocationDao.getCurrentLocation()
    }

    override fun delete() {
        userLocationDao.delete()
    }

}