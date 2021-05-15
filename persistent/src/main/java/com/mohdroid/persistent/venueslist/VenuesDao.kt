package com.mohdroid.persistent.venueslist

import androidx.room.*
import com.mohdroid.domain.entity.VenueEntity

@Dao
interface VenuesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(venueEntity: List<VenueEntity>)

    @Query("SELECT * FROM venue LIMIT :limit OFFSET :offset")
    fun getAllVenues(limit: Int, offset: Int): List<VenueEntity>

    @Query("SELECT  COUNT(id) FROM venue")
    fun getTotalCount(): Int

    @Query("DELETE FROM venue")
    fun removeVenues()

    @Query("SELECT * FROM venue WHERE id = :id")
    fun getVenueDetail(id: String): VenueEntity?

    @Update
    suspend fun updateVenue(entity: VenueEntity)
}