package com.mohdroid.service

import com.mohdroid.domain.entity.UserLocationEntity
import com.mohdroid.domain.persistent.UserLocationPersistent
import com.mohdroid.domain.service.UserLocationService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocationServiceServiceImpl @Inject constructor(
    private val userLocationPersistent: UserLocationPersistent
) : UserLocationService {

    override suspend fun getCurrentLocation(): UserLocationEntity? {
        var local: UserLocationEntity? = null
        withContext(IO) {
            local = userLocationPersistent.getCurrentLocation()
        }
        return local
    }

    override suspend fun saveLocation(userLocationEntity: UserLocationEntity) {
        withContext(IO) {
            userLocationPersistent.delete()
            userLocationPersistent.insertCurrentLocation(userLocationEntity)
        }

    }


}