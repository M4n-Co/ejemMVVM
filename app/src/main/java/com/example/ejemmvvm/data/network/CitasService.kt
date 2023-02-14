package com.example.ejemmvvm.data.network

import com.example.ejemmvvm.core.RetrofitHelper
import com.example.ejemmvvm.data.model.CitaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CitasService @Inject constructor(
    private val api:CitasAPI
    ){

    suspend fun getCitas():List<CitaModel>{
        return withContext(Dispatchers.IO){
            val response = api.getCitas()
            response.body() ?: emptyList()
        }
    }
}