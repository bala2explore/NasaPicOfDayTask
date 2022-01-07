package com.nasa.pic.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.nasa.pic.data.NasaRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NasaViewModel @Inject constructor(private val nasaRepository: NasaRepository) :
    ViewModel() {

    val nasaApi by lazy { nasaRepository.observeNasaApi() }


}