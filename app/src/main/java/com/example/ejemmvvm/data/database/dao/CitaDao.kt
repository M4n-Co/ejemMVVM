package com.example.ejemmvvm.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ejemmvvm.data.database.entities.CitaEntity

@Dao
interface CitaDao {

    @Query("SELECT * FROM tab_citasE ORDER BY autorE DESC")
    suspend fun getCitas():List<CitaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(citas:List<CitaEntity>)

    @Query("DELETE FROM tab_citasE")
    suspend fun deleteCitas()
}