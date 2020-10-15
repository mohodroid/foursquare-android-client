package com.mohdroid.domain.common

import com.mohdroid.domain.dto.Location
import com.mohdroid.domain.dto.Venue
import com.mohdroid.domain.dto.VenueBestPhoto
import com.mohdroid.domain.dto.VenueDetail
import com.mohdroid.domain.entity.UpdatedVenueEntity
import com.mohdroid.domain.entity.VenueBestPhotoEntity
import com.mohdroid.domain.entity.VenueEntity
import com.mohdroid.domain.entity.VenueLocationEntity

/**
 * Converts the given venue to corresponding entity.
 */
fun Venue.convertToVenueEntity(): VenueEntity {
    return VenueEntity(
        id,
        name,
        location.convertToLocationEntity(),
        rating
    )
}

/**
 * Converts the given location to corresponding entity.
 */
fun Location.convertToLocationEntity(): VenueLocationEntity {
    return VenueLocationEntity(
        address, city, lat, lng
    )
}

/**
 * Converts the given best photo to corresponding entity.
 */
fun VenueBestPhoto.convertToBestPhotoEntity(): VenueBestPhotoEntity {
    return VenueBestPhotoEntity(prefix, suffix, width, height)
}

/**
 * Coverts the given venue detail to corresponding entity.
 */
fun VenueDetail.convertToUpdateVenueEntity(): UpdatedVenueEntity {
    return UpdatedVenueEntity(description, rating, bestPhoto.convertToBestPhotoEntity())
}

/**
 * Returns the best photo full url.
 */
fun VenueBestPhotoEntity.getPhotoUrl(): String {
    val x = "x"
    return "$prefix$width$x$height$suffix"
}