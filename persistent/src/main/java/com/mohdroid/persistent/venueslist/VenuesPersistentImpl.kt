package com.mohdroid.persistent.venueslist

import com.mohdroid.domain.entity.UpdatedVenueEntity
import com.mohdroid.domain.entity.VenueEntity
import com.mohdroid.domain.persistent.VenuesPersistent
import com.mohdroid.persistent.DataBase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VenuesPersistentImpl @Inject constructor(
    dataBase: DataBase
) : VenuesPersistent {

    private var venuesDao: VenuesDao = dataBase.venuesListDao()
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