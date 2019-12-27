package com.example.weatherapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherInfo(
    val `data`: Data = Data()
) : Parcelable

@Parcelize
data class Data(
    val current_condition: List<CurrentCondition> = listOf(),
    val weather: List<Weather> = listOf()
) : Parcelable

@Parcelize
data class CurrentCondition(
    val FeelsLikeC: String = "",
    val FeelsLikeF: String = "",
    val cloudcover: String = "",
    val humidity: String = "",
    val observation_time: String = "",
    val precipInches: String = "",
    val precipMM: String = "",
    val pressure: String = "",
    val pressureInches: String = "",
    val temp_C: String = "",
    val temp_F: String = "",
    val uvIndex: Int = 0,
    val visibility: String = "",
    val visibilityMiles: String = "",
    val weatherCode: String = "",
    val weatherDesc: List<WeatherDesc> = listOf(),
    val weatherIconUrl: List<WeatherIconUrl> = listOf(),
    val winddir16Point: String = "",
    val winddirDegree: String = "",
    val windspeedKmph: String = "",
    val windspeedMiles: String = ""
) : Parcelable

@Parcelize
data class Weather(
    val avgtempC: String = "",
    val avgtempF: String = "",
    val date: String = "",
    val maxtempC: String = "",
    val maxtempF: String = "",
    val mintempC: String = "",
    val mintempF: String = "",
    val sunHour: String = "",
    val totalSnow_cm: String = "",
    val uvIndex: String = ""
) : Parcelable

@Parcelize
data class WeatherDesc(
    val value: String = ""
) : Parcelable

@Parcelize
data class WeatherIconUrl(
    val value: String = ""
) : Parcelable