package com.nasa.pic.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nasa.pic.di.ViewModelKey
import com.nasa.pic.presentation.viewmodel.NasaViewModel
import com.nasa.pic.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NasaViewModel::class)
    abstract fun nasaListViewModel(nasaViewModel: NasaViewModel): ViewModel
}