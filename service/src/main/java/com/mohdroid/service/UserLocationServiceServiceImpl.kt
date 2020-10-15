package com.mohdroid.service

import com.mohdroid.domain.entity.UserLocationEntity
import com.mohdroid.domain.repository.UserLocationRepository
import com.mohdroid.domain.service.UserLocationService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserLocationServiceServiceImpl @Inject constructor(
    private val userLocationRepository: UserLocationRepository
) : UserLocationService {

    override suspend fun getCurrentLocation(): UserLocationEntity? {
        var local: UserLocationEntity? = null
        withContext(IO) {
            local = userLocationRepository.getCurrentLocation()
        }
        return local
    }

    override suspend fun saveLocation(userLocationEntity: UserLocationEntity) {
        withContext(IO) {
            userLocationRepository.delete()
            userLocationRepository.insertCurrentLocation(userLocationEntity)
        }

    }


}