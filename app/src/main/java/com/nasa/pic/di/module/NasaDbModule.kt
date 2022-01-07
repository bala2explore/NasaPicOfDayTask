package com.nasa.pic.di.module

import android.content.Context
import androidx.room.Room
import com.nasa.pic.data.local.NasaDao
import com.nasa.pic.data.local.NasaDb
import com.nasa.pic.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NasaDbModule {
    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): NasaDb = Room
        .databaseBuilder(context, NasaDb::class.java, "Nasaapp.db")
        .build()

    @Provides
    @Singleton
    fun provideDao(nasaDb: NasaDb): NasaDao = nasaDb.NasaDao()
}
