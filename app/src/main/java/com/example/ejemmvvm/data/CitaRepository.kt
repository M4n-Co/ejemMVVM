package com.example.ejemmvvm.data

import com.example.ejemmvvm.data.database.dao.CitaDao
import com.example.ejemmvvm.data.database.entities.CitaEntity
import com.example.ejemmvvm.data.network.CitasService
import com.example.ejemmvvm.domain.model.Cita
import com.example.ejemmvvm.domain.model.toDomain
import javax.inject.Inject

class CitaRepository @Inject constructor(
    private val api : CitasService,
    private val citaDao: CitaDao
){

    suspend fun getAllCitasFromAPI():List<Cita>{
        val response = api.getCitas()
        return response.map { it.toDomain() }
    }

    suspend fun getAllCitassFromDB():List<Cita> {
        val response = citaDao.getCitas()
        return response.map { it.toDomain() }
    }

    suspend fun insertCitas(citas:List<CitaEntity>){
        citaDao.insertAll(citas)
    }

    suspend fun clearCitas() {
        citaDao.deleteCitas()
    }
}