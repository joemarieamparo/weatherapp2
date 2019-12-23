package com.example.weatherapp.data

import com.example.weatherapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

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

private val retrofit: Retrofit by lazy { Retrofit.Builder()
    .baseUrl(BuildConfig.HOSTNAME)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
}

private val apiService: ApiService = retrofit.create(
    ApiService::class.java)

fun getApiService() = apiService


interface ApiService {
    @GET("search.ashx")
    suspend fun search(@Query("q") q: String,
                     @Query("format") format: String = "json",
                     @Query("num_of_results") noOfResult: Int = 3,
                     @Query("key") key: String = BuildConfig.API_KEY): SearchResult
}