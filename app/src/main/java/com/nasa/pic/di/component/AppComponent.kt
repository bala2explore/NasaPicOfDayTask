package com.nasa.pic.di.component

import com.nasa.pic.NasaApplication
import com.nasa.pic.di.module.ContextModule
import com.nasa.pic.di.module.NasaDbModule
import com.nasa.pic.di.module.RetrofitModule
import com.nasa.pic.di.module.ViewModelModule

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RetrofitModule::class, NasaDbModule::class, ContextModule::class, ViewModelModule::class]
)
interface AppComponent {
    fun inject(nasaApplication: NasaApplication)
}