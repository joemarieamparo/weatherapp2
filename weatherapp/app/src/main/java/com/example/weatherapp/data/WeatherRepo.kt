package com.example.weatherapp.data

import java.lang.Exception

/**
 * This class handles data from either api or db
 */
class WeatherRepo(private val apiService: ApiService) {

    suspend fun searchCity(city: String): SearchResult {
        try {
            return apiService.search(city)
        } catch (e: Exception) {
            throw e
        }
    }
}