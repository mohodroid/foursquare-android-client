package com.mohdroid.domain.entity


/**
 * Encapsulates the venue best photo detail.
 *
 * @param prefix Represents photo base url.
 * @param suffix Represents photo url path.
 * @param width Represents the photo width.
 * @param height Represents the photo height.
 *
 * @author Mohdroid
 */
data class VenueBestPhotoEntity(
    val prefix: String,
    val suffix: String,
    val width: Int,
    val height: Int
)