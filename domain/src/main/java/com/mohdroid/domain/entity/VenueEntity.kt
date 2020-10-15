package com.mohdroid.domain.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Encapsulates the venue detail.
 *
 * @author Mohdroid
 */
@Entity(tableName = "venue")
data class VenueEntity(
    @PrimaryKey val id: String,
    val name: String,
    @Embedded
    val location: VenueLocationEntity,
    var rating: Float,
    var modified: Boolean = false
) {

    /**
     * Represents the venue description.
     */
    var description: String? = null

    /**
     * Encapsulates the venue photo detail.
     */
    @Embedded
    var bestPhoto: VenueBestPhotoEntity? = null
}