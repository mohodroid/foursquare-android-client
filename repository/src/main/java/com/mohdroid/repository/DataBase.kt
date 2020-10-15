package com.mohdroid.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohdroid.domain.entity.UserLocationEntity
import com.mohdroid.domain.entity.VenueEntity
import com.mohdroid.repository.userlocation.UserLocationDao
import com.mohdroid.repository.venueslist.VenuesDao

@Database(
    entities = [VenueEntity::class,
    UserLocationEntity::class],
    exportSchema = true,
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract fun venuesListDao(): VenuesDao

    abstract fun userLocationDao(): UserLocationDao

}