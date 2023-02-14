package com.example.ejemmvvm.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ejemmvvm.domain.model.Cita

@Entity(tableName = "tab_citasE")
data class CitaEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name = "citaE") val citaE:String,
    @ColumnInfo(name = "autorE") val autorE:String)

fun Cita.toDataBase() = CitaEntity(citaE = cita, autorE = cita)