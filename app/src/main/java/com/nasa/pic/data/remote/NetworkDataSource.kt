package com.nasa.pic.data.remote

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkDataSource @Inject constructor(
    private val nasaWebService: NasaWebService
) : BaseDataSource(){

}