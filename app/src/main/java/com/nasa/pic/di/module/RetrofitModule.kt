package com.nasa.pic.di.module

import com.nasa.pic.BuildConfig
import com.nasa.pic.data.remote.NasaWebService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    companion object {
        private const val NASA_BASE_URL = "https://api.nasa.gov/"
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): NasaWebService =
        retrofit.create(NasaWebService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(NASA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(provideApiKeyInterceptor(BuildConfig.NASA_API_KEY))
            .build()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(apiKey: String): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val url = request.url.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()
            val newRequest = request.newBuilder()
                .url(url)
                .build()
            chain.proceed(newRequest)
        }
    }
}