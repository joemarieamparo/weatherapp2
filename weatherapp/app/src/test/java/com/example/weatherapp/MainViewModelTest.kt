package com.example.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapp.data.WeatherRepo
import com.example.weatherapp.data.models.*
import com.example.weatherapp.utils.*
import com.example.weatherapp.view.MainViewModel
import com.google.common.truth.Truth
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    lateinit var resultInfoTestObject: ResultInfo

    @Before
    fun setup() {
        resultInfoTestObject = ResultInfo(listOf(AreaName("Area")), listOf(Country("Country")),
            "lat", "lon", "pop", listOf(Region("Region")))
    }

    @Test
    fun searchCitySuccess() {
        val list = listOf(resultInfoTestObject, resultInfoTestObject)
        val subject = MainViewModel(
            WeatherRepo(
                FakeSearchApiService(SearchResult(search_api = SearchApi(result = list))),
                FakeWeatherApiService(),
                FakeCityDao()
            )
        )
        subject.searchCity("test")
        Truth.assertThat(subject.searchCitiesLiveData.getValueForTest()).isEqualTo(list.map { it.cityInfo() })
    }

    @Test
    fun getWeatherSuccess() {
        val result = WeatherInfo()
        val subject = MainViewModel(
            WeatherRepo(
                FakeSearchApiService(),
                FakeWeatherApiService(result),
                FakeCityDao()
            )
        )

        subject.getWeather("test", "test")
        Truth.assertThat(subject.cityWeatherLiveData.getValueForTest()).isEqualTo(result)
    }
}
