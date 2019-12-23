package com.example.weatherapp.data

import com.example.weatherapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * This calls handles network and api related functions
 */
private val interceptor = run {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.apply {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }
}

private val okHttpClient: OkHttpClient by lazy {
    OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}

private fun getRetrofit(hostname: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(hostname)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private val searchApiService: SearchApiService = getRetrofit(BuildConfig.HOSTNAME_SEARCH).create(
    SearchApiService::class.java)

private val weatherApiService: WeatherApiService = getRetrofit(BuildConfig.HOSTNAME_WEATHER).create(
    WeatherApiService::class.java)

fun getSearchApiService() = searchApiService

fun getWeatherApiService() = weatherApiService

interface SearchApiService {
    @GET("search.ashx")
    suspend fun search(@Query("q") q: String,
                     @Query("format") format: String = "json",
                     @Query("num_of_results") noOfResult: Int = 3,
                     @Query("key") key: String = BuildConfig.API_KEY): SearchResult
}

interface WeatherApiService {
    @GET("weather.ashx")
    suspend fun getWeather(@Query("q") q: String,
                           @Query("date") date: String = "today",
                           @Query("cc") cc: String ="yes",
                           @Query("format") format: String = "json",
                           @Query("showlocaltime") showlocaltime: String = "yes",
                           @Query("key") key: String = BuildConfig.API_KEY) : WeatherInfo
}