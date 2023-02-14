package com.example.ejemmvvm.UI.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.ejemmvvm.databinding.ActivityMainBinding
import com.example.ejemmvvm.UI.viewmodel.CitaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val citaViewModel : CitaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        citaViewModel.onCreate()

        citaViewModel.citaM.observe(this, Observer { citas->
            binding.tvCita.text = citas?.cita
            binding.tvAutor.text = citas?.autor
        })
        citaViewModel.isLoading.observe(this, Observer {
            binding.pb.isVisible = it
        })

        binding.clMain.setOnClickListener {
            citaViewModel.citaRandom()
        }
    }
}