package com.mohdroid.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_location")
data class UserLocationEntity(
    val lat: Double,
    val lng: Double
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}