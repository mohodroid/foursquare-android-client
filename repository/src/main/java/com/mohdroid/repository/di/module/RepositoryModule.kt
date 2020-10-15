package com.mohdroid.repository.di.module

import android.app.Application
import androidx.room.Room
import com.mohdroid.domain.DATABASE_NAME
import com.mohdroid.domain.repository.UserLocationRepository
import com.mohdroid.domain.repository.VenuesRepository
import com.mohdroid.repository.DataBase
import com.mohdroid.repository.userlocation.UserLocationRepositoryImpl
import com.mohdroid.repository.venueslist.VenuesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule(ctx: Application) {

    private var database: DataBase =
        Room.databaseBuilder(ctx, DataBase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDataBase(): DataBase = database

    @Singleton
    @Provides
    fun provideVenuesListRepository(
        dataBase: DataBase
    ): VenuesRepository = VenuesRepositoryImpl(dataBase.venuesListDao())

    @Singleton
    @Provides
    fun provideUserLocationRepository(
        dataBase: DataBase
    ) : UserLocationRepository = UserLocationRepositoryImpl(dataBase.userLocationDao())

}