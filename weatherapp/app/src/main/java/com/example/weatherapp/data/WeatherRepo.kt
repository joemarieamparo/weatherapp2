package com.example.weatherapp.data

import java.lang.Exception

/**
 * This class handles data from either api or db
 */
class WeatherRepo {

    private val searchApiService = getSearchApiService()
    private val weatherApiService = getWeatherApiService()

    suspend fun searchCity(city: String): SearchResult {
        try {
            return searchApiService.search(city)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getWeather(query: String): WeatherInfo {
        try {
            return  weatherApiService.getWeather(query)
        } catch (e: Exception) {
            throw e
        }
    }
}