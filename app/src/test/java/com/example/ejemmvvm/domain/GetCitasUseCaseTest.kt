package com.example.ejemmvvm.domain

import com.example.ejemmvvm.data.CitaRepository
import com.example.ejemmvvm.domain.model.Cita
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetCitasUseCaseTest{

    @RelaxedMockK
    private lateinit var citaRepository: CitaRepository
    lateinit var getCitasUseCase: GetCitasUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getCitasUseCase = GetCitasUseCase(citaRepository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        //Given
        coEvery { citaRepository.getAllCitasFromAPI() } returns emptyList()

        //When
        getCitasUseCase()

        //Then
        coVerify(exactly = 1) { citaRepository.getAllCitassFromDB() }
    }

    @Test
    fun `when the api return something then get values from database`() = runBlocking {
        //Given
        val myList = listOf(Cita("Déjame un comentario", "AristiDevs"))
        coEvery { citaRepository.getAllCitasFromAPI() } returns myList
        //When
        val response = getCitasUseCase()
        //Then
        coVerify(exactly = 1) { citaRepository.clearCitas() }
        coVerify(exactly = 1) { citaRepository.insertCitas(any()) }
        coVerify(exactly = 0) { citaRepository.getAllCitassFromDB() }
        assert(response == myList)
    }
}