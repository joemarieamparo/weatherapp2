package com.example.weatherapp.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.ResultInfo
import com.example.weatherapp.data.WeatherInfo
import com.example.weatherapp.data.WeatherRepo
import com.example.weatherapp.utils.singleArgViewModelFactory
import kotlinx.coroutines.launch

class MainViewModel(val repo: WeatherRepo) : ViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::MainViewModel)
    }

    val cityToForecastLiveData = MutableLiveData<ResultInfo>()

    val cityWeatherLiveData = MutableLiveData<WeatherInfo>()

    val searchResultsLiveData = MutableLiveData<List<ResultInfo>>()

    fun searchCity(query: String) {
        viewModelScope.launch {
            searchResultsLiveData.value = repo.searchCity(query).search_api.result
        }
    }

    fun getWeather(latitude: String, longitude: String) {
        viewModelScope.launch {
            cityWeatherLiveData.value = repo.getWeather("$latitude, $longitude")
        }
    }
}