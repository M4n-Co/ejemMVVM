package com.example.ejemmvvm.UI.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejemmvvm.domain.GetCitasUseCase
import com.example.ejemmvvm.domain.GetRandomCitaUseCase
import com.example.ejemmvvm.domain.model.Cita
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitaViewModel @Inject constructor(
    private val getCitasUseCase:GetCitasUseCase,
    private val getRandomCitaUseCase : GetRandomCitaUseCase
) : ViewModel() {

    val citaM = MutableLiveData<Cita>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getCitasUseCase()
            if (!result.isNullOrEmpty()){
                citaM.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun citaRandom(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val cita = getRandomCitaUseCase()
            if (cita!=null){
                citaM.postValue(cita)
            }
            isLoading.postValue(false)
        }
    }
}