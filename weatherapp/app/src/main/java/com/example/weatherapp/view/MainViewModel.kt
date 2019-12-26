package com.example.weatherapp.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.R
import com.example.weatherapp.data.City
import com.example.weatherapp.data.WeatherRepo
import com.example.weatherapp.data.models.Weather
import com.example.weatherapp.utils.cityInfo
import com.example.weatherapp.utils.singleArgViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val repo: WeatherRepo) : ViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::MainViewModel)
    }

    val cityToForecastLiveData = MutableLiveData<City>()

    val cityWeatherLiveData = MutableLiveData<Pair<City, List<Weather>>>()

    val searchCitiesLiveData = MutableLiveData<List<City>>()

    val toastMsg = MutableLiveData<Int>()

    fun searchCity(query: String) {
        viewModelScope.launch {
            try {
                val result = repo.searchCity(query).search_api.result.map { it.cityInfo() }
                if (result.isNotEmpty())
                    searchCitiesLiveData.value = result
                else
                    toastMsg.value = R.string.no_result_found
            } catch (e: Exception) {
                toastMsg.value = R.string.no_result_found
            }
        }
    }

    fun getWeather(latitude: String, longitude: String) {
        viewModelScope.launch {
            try {
                val result = repo.getWeather("$latitude, $longitude").data

                val currentCondition = result.current_condition[0]

                var city = cityToForecastLiveData.value!!
                city.weatherIconUrl = currentCondition.weatherIconUrl[0].value
                city.weatherDesc = currentCondition.weatherDesc[0].value
                city.humidity = currentCondition.humidity
                city.temp = currentCondition.temp_C

                cityWeatherLiveData.value = Pair(city, result.weather)
            } catch (e: Exception) {
                toastMsg.value = R.string.server_error_encountered
            }

        }
    }

    fun saveCityInfoToDb(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.delete(city)
                repo.saveCityInfoToDb(city)
            } catch (e: Exception) {
                toastMsg.value = R.string.saving_to_db_failed
            }
        }
    }

    fun loadCities() {
        viewModelScope.launch(Dispatchers.Main) {
            val cities = withContext(Dispatchers.IO) {
                repo.getCities()
            }
            if (!cities.isNullOrEmpty()) {
                searchCitiesLiveData.value = cities.sortedByDescending { it.id }.take(10)
            }
        }
    }
}


