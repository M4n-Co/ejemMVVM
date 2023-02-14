package com.example.ejemmvvm.data.network

import com.example.ejemmvvm.data.model.CitaModel
import retrofit2.Response
import retrofit2.http.GET

interface CitasAPI {
    @GET("/.json")
    suspend fun getCitas():Response<List<CitaModel>>
}