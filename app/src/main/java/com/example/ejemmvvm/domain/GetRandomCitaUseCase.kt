package com.example.ejemmvvm.domain

import com.example.ejemmvvm.data.CitaRepository
import com.example.ejemmvvm.data.model.CitaModel
import com.example.ejemmvvm.domain.model.Cita
import javax.inject.Inject

class GetRandomCitaUseCase @Inject constructor(
    private val repository: CitaRepository
) {

    suspend operator fun invoke(): Cita?{
        val Citas = repository.getAllCitassFromDB()
        if (!Citas.isNullOrEmpty()){
            val numberRandom = (0..Citas.size-1).random()
            return Citas[numberRandom]
        }
        return null
    }
}