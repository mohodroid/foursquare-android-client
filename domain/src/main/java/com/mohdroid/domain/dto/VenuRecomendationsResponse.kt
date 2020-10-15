package com.mohdroid.domain.dto

data class VenuRecomendationsResponse(
    val response: Result
)

/**
 * Represents a location of venue
 *
 * @property address street address of venue
 * @property city city name of venue
 * @property lat Latitude  of the venue
 * @property lng longitude of the venue
 *
 */
data class Location(
    val address: String,
    val city: String,
    val lat: Double,
    val lng: Double
)

/**
 * @property id A unique string identifier for this venue.
 * @property name The best known name for this venue.
 * @property location location of the venue
 * @property rating Numerical rating of the venue (0 through 10).Not all venues will have a rating.
 */
data class Venue(
    val id: String,
    val name: String,
    val location: Location,
    val rating: Float
)

/**
 * @property venue Detail about the venue such as {rating..}
 */
data class Item(
    val venue: Venue
)

/**
 * An array of objects representing groups of recommendations.
 *
 * @property type such as “recommended” a human-readable (eventually localized)
 * @property name such as “Recommended Places
 * @property items recommendation objects.
 */
data class Group(
    val type: String,
    val name: String,
    val items: ArrayList<Item>
)

/**
 * @property totalResults number of Venues
 * @property groups An array of objects representing groups of recommendations
 */
data class Result(
    val totalResults: Int,
    val groups: ArrayList<Group>
)




