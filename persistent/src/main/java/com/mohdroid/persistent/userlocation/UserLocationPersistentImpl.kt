package com.mohdroid.persistent.userlocation

import com.mohdroid.domain.entity.UserLocationEntity
import com.mohdroid.domain.persistent.UserLocationPersistent
import com.mohdroid.persistent.DataBase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocationPersistentImpl @Inject constructor(
    dataBase: DataBase
): UserLocationPersistent {

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