package com.mohdroid.domain.persistent

import com.mohdroid.domain.entity.UpdatedVenueEntity
import com.mohdroid.domain.entity.VenueEntity

interface VenuesPersistent {

    fun insertVenues(venuesListEntity: List<VenueEntity>)

    fun getVenues(limit: Int, offset: Int): List<VenueEntity>

    fun getTotalCount(): Int

    fun removeOldVenues()

    fun getVenueDetail(id: String): VenueEntity?

    suspend fun updateVenue(entity: VenueEntity, updatedVenueEntity: UpdatedVenueEntity): VenueEntity

}