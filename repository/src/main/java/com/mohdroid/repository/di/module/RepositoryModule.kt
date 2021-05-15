package com.mohdroid.repository.di.module

import android.app.Application
import androidx.room.Room
import com.mohdroid.domain.DATABASE_NAME
import com.mohdroid.domain.repository.UserLocationRepository
import com.mohdroid.domain.repository.VenuesRepository
import com.mohdroid.repository.DataBase
import com.mohdroid.repository.userlocation.UserLocationRepositoryImpl
import com.mohdroid.repository.venueslist.VenuesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RepositoryModule.BindModule::class])
class RepositoryModule(ctx: Application) {

    private var database: DataBase =
        Room.databaseBuilder(ctx, DataBase::class.java, DATABASE_NAME).build()


    @Provides
    fun provideDataBase(): DataBase = database

    @Module
    abstract class BindModule {
        @Binds
        abstract fun provideVenuesListRepository(venuesRepositoryImpl: VenuesRepositoryImpl): VenuesRepository


        @Binds
        abstract fun provideUserLocationRepository(userLocationRepositoryImpl: UserLocationRepositoryImpl): UserLocationRepository
    }


}