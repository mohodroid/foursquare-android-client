package com.mohdroid.repository.userlocation

import com.mohdroid.domain.entity.UserLocationEntity
import com.mohdroid.domain.repository.UserLocationRepository
import com.mohdroid.repository.DataBase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocationRepositoryImpl @Inject constructor(
    dataBase: DataBase
): UserLocationRepository {

    private val userLocationDao: UserLocationDao = dataBase.userLocationDao()

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