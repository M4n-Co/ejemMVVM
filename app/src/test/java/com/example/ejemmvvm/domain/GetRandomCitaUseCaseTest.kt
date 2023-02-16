package com.example.ejemmvvm.domain

import com.example.ejemmvvm.data.CitaRepository
import com.example.ejemmvvm.domain.model.Cita
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetRandomCitaUseCaseTest{

    @RelaxedMockK
    private lateinit var citaRepository: CitaRepository
    lateinit var getRandomCitaUseCase: GetRandomCitaUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRandomCitaUseCase = GetRandomCitaUseCase(citaRepository)
    }

    @Test
    fun `when database is empty then return null`() = runBlocking {
        //Given
        coEvery { citaRepository.getAllCitassFromDB() } returns emptyList()

        //When
        val response = getRandomCitaUseCase()

        //Then
        assert(response == null)
    }

    @Test
    fun `when database is not empty then return quote`() = runBlocking {

        //Given
        val quoteList = listOf(Cita("Holi", "AristiDevs"))
        coEvery { citaRepository.getAllCitassFromDB() } returns quoteList

        //When
        val response = getRandomCitaUseCase()

        //Then
        assert(response == quoteList.first())
    }
}