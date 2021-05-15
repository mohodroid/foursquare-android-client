package com.mohdroid.persistent.userlocation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mohdroid.domain.entity.UserLocationEntity

@Dao
interface UserLocationDao {

    @Insert
    fun insertCurrentLocation(userLocation: UserLocationEntity)

    @Query("SELECT * FROM user_location")
    fun getCurrentLocation(): UserLocationEntity

    @Query("DELETE FROM user_location")
    fun delete()

}