package com.example.ejemmvvm.domain

import com.example.ejemmvvm.data.CitaRepository
import com.example.ejemmvvm.data.database.entities.toDataBase
import com.example.ejemmvvm.data.model.CitaModel
import com.example.ejemmvvm.domain.model.Cita
import javax.inject.Inject

class GetCitasUseCase @Inject constructor(
    private val repository : CitaRepository
    ) {

    suspend operator fun invoke():List<Cita>{
        val citas = repository.getAllCitasFromAPI()

        return if (citas.isNotEmpty()){
            repository.clearCitas()
            repository.insertCitas(citas.map { it.toDataBase() })
            citas
        }else{
            repository.getAllCitassFromDB()
        }
    }
}