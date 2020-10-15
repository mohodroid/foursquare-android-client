package com.mohdroid.repository.venueslist

import com.mohdroid.domain.entity.UpdatedVenueEntity
import com.mohdroid.domain.entity.VenueEntity
import com.mohdroid.domain.repository.VenuesRepository
import javax.inject.Inject

class VenuesRepositoryImpl @Inject constructor(
    private val venuesDao: VenuesDao
) : VenuesRepository {


    override fun insertVenues(venuesListEntity: List<VenueEntity>) {
        venuesDao.insert(venuesListEntity)
    }

    override fun getVenues(limit: Int, offset: Int): List<VenueEntity> {
        return venuesDao.getAllVenues(limit, offset)
    }

    override fun getTotalCount(): Int {
        return venuesDao.getTotalCount()
    }

    override fun removeOldVenues() {
        return venuesDao.removeVenues()
    }

    override fun getVenueDetail(id: String): VenueEntity? {
        return venuesDao.getVenueDetail(id)
    }

    override suspend fun updateVenue(
        entity: VenueEntity,
        updatedVenueEntity: UpdatedVenueEntity
    ): VenueEntity {
        entity.description = updatedVenueEntity.description
        entity.rating = updatedVenueEntity.rating
        entity.bestPhoto = updatedVenueEntity.bestPhoto
        entity.modified = true

        venuesDao.updateVenue(entity)
        return entity
    }


}