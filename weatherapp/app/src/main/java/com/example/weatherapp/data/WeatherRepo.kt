package com.example.weatherapp.data

import android.content.Context
import com.example.weatherapp.data.models.SearchResult
import com.example.weatherapp.data.models.WeatherInfo

/**
 * This class handles data from either api or db
 */
class WeatherRepo(context: Context) {

    private val searchApiService = getSearchApiService()
    private val weatherApiService = getWeatherApiService()
    private val cityDao = getDatabase(context).cityDao

    suspend fun searchCity(city: String): SearchResult {
        return searchApiService.search(city)
    }

    suspend fun getWeather(query: String): WeatherInfo {
        return  weatherApiService.getWeather(query)
    }

    fun saveCityInfoToDb(city: City) {
        cityDao.insert(city)
    }

    fun getCities(): List<City> {
        return cityDao.cities
    }

    fun delete(city: City){
        cityDao.delete(city.lat, city.lon)
    }
}