package com.nasa.pic.data

import androidx.lifecycle.distinctUntilChanged
import com.nasa.pic.data.local.NasaDao
import com.nasa.pic.data.remote.NetworkDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NasaRepository @Inject constructor(
    private val nasaDao: NasaDao,
    private val networkDataSource: NetworkDataSource,
) {

    fun observeNasaApi() = resultLiveData(
        databaseQuery = { nasaDao.getAllPictures() },
        networkCall = { networkDataSource.fetchNasaApi() },
        saveCallResult = { nasaDao.insertPicture(it) })
        .distinctUntilChanged()
}

