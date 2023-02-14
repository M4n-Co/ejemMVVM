package com.example.ejemmvvm.di

import android.content.Context
import androidx.room.Room
import com.example.ejemmvvm.data.database.CitasDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    val NAME_DB = "citasDB"

    @Singleton
    @Provides
    fun providesRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CitasDB::class.java, NAME_DB).build()

    @Singleton
    @Provides
    fun provideCitasDao(db: CitasDB) = db.getCitaDao()
}