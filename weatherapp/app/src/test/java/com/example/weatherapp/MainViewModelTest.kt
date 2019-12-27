package com.example.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapp.data.City
import com.example.weatherapp.data.WeatherRepo
import com.example.weatherapp.data.models.*
import com.example.weatherapp.utils.*
import com.example.weatherapp.view.MainViewModel
import com.google.common.truth.Truth
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Test
    fun searchCitySuccess() {
        val resultInfoTestObject = ResultInfo(listOf(AreaName("Area")), listOf(Country("Country")),
            "lat", "lon", "pop", listOf(Region("Region")))
        val list = listOf(resultInfoTestObject, resultInfoTestObject)
        val subject = MainViewModel(
            WeatherRepo(
                FakeSearchApiService(SearchResult(search_api = SearchApi(result = list))),
                FakeWeatherApiService(),
                FakeCityDao()
            )
        )
        subject.searchCity("test")
        Truth.assertThat(subject.searchCitiesLiveData.getValueForTest())
            .isEqualTo(list.map { it.cityInfo() })
    }

    @Test
    fun searchCity_emptyList() {
        val subject = MainViewModel(
            WeatherRepo(
                FakeSearchApiService(SearchResult(search_api = SearchApi())),
                FakeWeatherApiService(),
                FakeCityDao()
            )
        )
        subject.searchCity("test")
        Truth.assertThat(subject.toastMsg.getValueForTest())
            .isEqualTo(R.string.no_result_found)
    }

    @Test
    fun getWeatherSuccess() {
        val city = City()
        val weatherIconUrl = listOf(WeatherIconUrl())
        val weatherDesc = listOf(WeatherDesc())
        val weatherlist = listOf(Weather(), Weather())
        val currentCondition = listOf(CurrentCondition(weatherIconUrl = weatherIconUrl, weatherDesc = weatherDesc))

        val weatherInfoTestObject= WeatherInfo(data = Data(current_condition = currentCondition,
            weather = weatherlist))
        val subject = MainViewModel(
            WeatherRepo(
                FakeSearchApiService(),
                FakeWeatherApiService(weatherInfoTestObject),
                FakeCityDao()
            )
        )

        subject.cityToForecastLiveData.value = city

        subject.getWeather("test", "test")
        Truth.assertThat(subject.cityWeatherLiveData.getValueForTest())
            .isEqualTo(Pair(city, weatherInfoTestObject.data.weather))
    }

    @Test
    fun getWeather_emptyList() {
        val subject = MainViewModel(
            WeatherRepo(
                FakeSearchApiService(),
                FakeWeatherApiService(WeatherInfo()),
                FakeCityDao()
            )
        )

        subject.getWeather("test", "test")
        Truth.assertThat(subject.toastMsg.getValueForTest())
            .isEqualTo(R.string.server_error_encountered)
    }

    @Test
    fun saveCityInfoToDb() {
        val subject = MainViewModel(
            WeatherRepo(
                FakeSearchApiService(),
                FakeWeatherApiService(WeatherInfo()),
                FakeCityDao()
            )
        )
        subject.saveCityInfoToDb(City())
        Truth.assertThat(subject.toastMsg.getValueForTest()).isNull()
    }

    @Test
    fun loadCitiesSuccess() {
            val subject = MainViewModel(
                WeatherRepo(
                    FakeSearchApiService(),
                    FakeWeatherApiService(WeatherInfo()),
                    FakeCityDao()
                )
            )
            subject.loadCities()
            Truth.assertThat(subject.searchCitiesLiveData.getValueForTest()).isNull()
    }
}
