package com.mohdroid.persistent

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohdroid.domain.entity.UserLocationEntity
import com.mohdroid.domain.entity.VenueEntity
import com.mohdroid.persistent.userlocation.UserLocationDao
import com.mohdroid.persistent.venueslist.VenuesDao

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