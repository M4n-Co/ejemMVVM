package com.example.ejemmvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ejemmvvm.data.database.dao.CitaDao
import com.example.ejemmvvm.data.database.entities.CitaEntity

@Database(entities = [CitaEntity::class], version = 1)
abstract class CitasDB : RoomDatabase(){

    abstract fun getCitaDao():CitaDao
}