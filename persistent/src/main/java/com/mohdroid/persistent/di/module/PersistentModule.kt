package com.mohdroid.persistent.di.module

import android.app.Application
import androidx.room.Room
import com.mohdroid.domain.DATABASE_NAME
import com.mohdroid.domain.persistent.UserLocationPersistent
import com.mohdroid.domain.persistent.VenuesPersistent
import com.mohdroid.persistent.DataBase
import com.mohdroid.persistent.userlocation.UserLocationPersistentImpl
import com.mohdroid.persistent.venueslist.VenuesPersistentImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [PersistentModule.BindModule::class])
class PersistentModule(ctx: Application) {

    private var database: DataBase =
        Room.databaseBuilder(ctx, DataBase::class.java, DATABASE_NAME).build()


    @Provides
    fun provideDataBase(): DataBase = database

    @Module
    abstract class BindModule {
        @Binds
        abstract fun provideVenuesListRepository(venuesRepositoryImpl: VenuesPersistentImpl): VenuesPersistent


        @Binds
        abstract fun provideUserLocationRepository(userLocationRepositoryImpl: UserLocationPersistentImpl): UserLocationPersistent
    }


}