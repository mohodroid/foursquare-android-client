package com.mohdroid.persistent.di.module

import android.app.Application
import android.content.Context
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
import javax.inject.Singleton

@Module(includes = [PersistentModule.BindModule::class])
class PersistentModule {

    @Singleton
    @Provides
    fun provideDataBase(ctx: Context): DataBase  {
        return Room.databaseBuilder(ctx, DataBase::class.java, DATABASE_NAME).build()
    }

    @Module
    abstract class BindModule {
        @Binds
        abstract fun provideVenuesListRepository(venuesRepositoryImpl: VenuesPersistentImpl): VenuesPersistent


        @Binds
        abstract fun provideUserLocationRepository(userLocationRepositoryImpl: UserLocationPersistentImpl): UserLocationPersistent
    }


}