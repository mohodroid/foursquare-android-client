package com.mohdroid.domain.dto

/**
 * Encapsulates the venue detail response.
 *
 * @param venue Encapsulates a venue detail.
 */
data class VenueDetailResponse(val venue: VenueDetail)

/**
 * Encapsulates a venue detail.
 *
 * @param id Represents the venue identifier.
 * @param description Represents the venue description.
 * @param rating Represents the venue rating.
 */
data class VenueDetail(
    val id: String,
    val description: String?,
    val rating: Float,
    val bestPhoto: VenueBestPhoto
)

/**
 * Encapsulates the detail of venue best photo.
 *
 * @param prefix Represents photo base url.
 * @param suffix Represents photo url path.
 * @param width Represents the photo width.
 * @param height Represents the photo height.
 */
data class VenueBestPhoto(
    val prefix: String,
    val suffix: String,
    val width: Int,
    val height: Int
)