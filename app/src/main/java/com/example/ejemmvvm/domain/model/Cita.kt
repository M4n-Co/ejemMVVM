package com.example.ejemmvvm.domain.model

import com.example.ejemmvvm.data.database.entities.CitaEntity
import com.example.ejemmvvm.data.model.CitaModel

data class Cita (val cita : String, val autor : String)

fun CitaModel.toDomain() = Cita(cita, autor)
fun CitaEntity.toDomain() = Cita(citaE, autorE)