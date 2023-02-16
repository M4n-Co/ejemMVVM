package com.example.ejemmvvm.UI.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ejemmvvm.data.CitaRepository
import com.example.ejemmvvm.domain.GetCitasUseCase
import com.example.ejemmvvm.domain.GetRandomCitaUseCase
import com.example.ejemmvvm.domain.model.Cita
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class CitaViewModelTest{

    @RelaxedMockK
    private lateinit var citasUseCase: GetCitasUseCase

    @RelaxedMockK
    private lateinit var getRandomCitaUseCase: GetRandomCitaUseCase

    @RelaxedMockK
    private lateinit var citaViewModel: CitaViewModel

    @get:Rule
    var rule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        citaViewModel = CitaViewModel(citasUseCase, getRandomCitaUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all quotes and set the first value`() = runTest{
        //Given
        val citaT = listOf(Cita("PruebaCita1", "PruebaAutor1"), Cita("PruebaCita2", "PruebaAutor2"))
        coEvery { citasUseCase() } returns citaT
        //When
        citaViewModel.onCreate()
        //Then
        assert(citaViewModel.citaM.value == citaT.first())
    }

    @Test
    fun `when randomQuoteUseCase return a quote set on the livedata`() = runTest {
        //Given
        val citaT = Cita("PruebaCita3", "PruebaAutor3")
        coEvery { getRandomCitaUseCase() } returns citaT
        //When
        citaViewModel.citaRandom()
        //Then
        assert(citaViewModel.citaM.value == citaT)
    }

    @Test
    fun `if randomQuoteUseCase return null keep the last value`() = runTest{
        //Given
        val citaT = Cita("PruebaCita4", "PruebaAutor4")
        citaViewModel.citaM.value = citaT
        coEvery { getRandomCitaUseCase() } returns null

        //When
        citaViewModel.citaRandom()
        //Then
        assert(citaViewModel.citaM.value == citaT)
    }

}
