package com.nasa.pic.data.remote

import com.nasa.pic.data.entity.NasaEntity
import retrofit2.Response
import retrofit2.http.GET

interface NasaWebService {
    @GET("planetary/apod")
    suspend fun getPictureOfTheDay(): Response<NasaEntity>
}