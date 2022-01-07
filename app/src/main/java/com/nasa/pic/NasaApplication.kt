package com.nasa.pic

import android.app.Application
import com.nasa.pic.di.component.AppComponent
import com.nasa.pic.di.component.DaggerAppComponent
import com.nasa.pic.di.module.ContextModule

class NasaApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var instance: NasaApplication
            private set
    }

    private val appComponent by lazy {
        DaggerAppComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent.inject(this)
    }
    fun getNasaAppComponent(): AppComponent = appComponent
}