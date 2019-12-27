package com.example.weatherapp.data

import com.example.weatherapp.data.models.SearchResult
import com.example.weatherapp.data.models.WeatherInfo

/**
 * This class handles data from either api or db
 */
class WeatherRepo(val searchApiService: SearchApiService,
                  val weatherApiService: WeatherApiService,
                  val cityDao: CityDao) {

    suspend fun searchCity(city: String): SearchResult {
        return searchApiService.search(city)
    }

    suspend fun getWeather(query: String): WeatherInfo {
        return weatherApiService.getWeather(query)
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