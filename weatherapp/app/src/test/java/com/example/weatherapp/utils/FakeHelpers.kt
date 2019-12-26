package com.example.weatherapp.utils

import com.example.weatherapp.data.City
import com.example.weatherapp.data.CityDao
import com.example.weatherapp.data.SearchApiService
import com.example.weatherapp.data.WeatherApiService
import com.example.weatherapp.data.models.SearchResult
import com.example.weatherapp.data.models.WeatherInfo

class FakeSearchApiService(var result: SearchResult = SearchResult()) : SearchApiService {

    override suspend fun search(
        q: String,
        format: String,
        noOfResult: Int,
        key: String
    ): SearchResult {
         return result
    }
}

class FakeWeatherApiService(var result: WeatherInfo = WeatherInfo()): WeatherApiService {
    override suspend fun getWeather(
        q: String,
        date: String,
        cc: String,
        format: String,
        showlocaltime: String,
        key: String
    ): WeatherInfo {
        return result
    }
}

class FakeCityDao(var cityList: List<City> = listOf()) : CityDao {
    override fun insert(city: City) {
    }

    override val cities: List<City>
        get() = cityList

    override fun delete(lat: String, lon: String) {
    }
}