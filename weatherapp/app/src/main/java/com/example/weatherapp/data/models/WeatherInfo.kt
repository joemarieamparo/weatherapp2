package com.example.weatherapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherInfo(
    val `data`: Data = Data()
) : Parcelable

@Parcelize
data class Data(
    val ClimateAverages: List<ClimateAverage> = listOf(),
    val current_condition: List<CurrentCondition> = listOf(),
    val request: List<Request> = listOf(),
    val time_zone: List<TimeZone> = listOf(),
    val weather: List<Weather> = listOf()
) : Parcelable

@Parcelize
data class ClimateAverage(
    val month: List<Month> = listOf()
) : Parcelable

@Parcelize
data class Month(
    val absMaxTemp: String = "",
    val absMaxTemp_F: String = "",
    val avgDailyRainfall: String = "",
    val avgMinTemp: String = "",
    val avgMinTemp_F: String = "",
    val index: String = "",
    val name: String = ""
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
data class Request(
    val query: String = "",
    val type: String = ""
) : Parcelable

@Parcelize
data class TimeZone(
    val localtime: String = "",
    val utcOffset: String = "",
    val zone: String = ""
) : Parcelable

@Parcelize
data class Weather(
    val astronomy: List<Astronomy> = listOf(),
    val avgtempC: String = "",
    val avgtempF: String = "",
    val date: String = "",
    val hourly: List<Hourly> = listOf(),
    val maxtempC: String = "",
    val maxtempF: String = "",
    val mintempC: String = "",
    val mintempF: String = "",
    val sunHour: String = "",
    val totalSnow_cm: String = "",
    val uvIndex: String = ""
) : Parcelable

@Parcelize
data class Astronomy(
    val moon_illumination: String = "",
    val moon_phase: String = "",
    val moonrise: String = "",
    val moonset: String = "",
    val sunrise: String = "",
    val sunset: String = ""
) : Parcelable

@Parcelize
data class Hourly(
    val DewPointC: String = "",
    val DewPointF: String = "",
    val FeelsLikeC: String = "",
    val FeelsLikeF: String = "",
    val HeatIndexC: String = "",
    val HeatIndexF: String = "",
    val WindChillC: String = "",
    val WindChillF: String = "",
    val WindGustKmph: String = "",
    val WindGustMiles: String = "",
    val chanceoffog: String = "",
    val chanceoffrost: String = "",
    val chanceofhightemp: String = "",
    val chanceofovercast: String = "",
    val chanceofrain: String = "",
    val chanceofremdry: String = "",
    val chanceofsnow: String = "",
    val chanceofsunshine: String = "",
    val chanceofthunder: String = "",
    val chanceofwindy: String = "",
    val cloudcover: String = "",
    val humidity: String = "",
    val precipInches: String = "",
    val precipMM: String = "",
    val pressure: String = "",
    val pressureInches: String = "",
    val tempC: String = "",
    val tempF: String = "",
    val time: String = "",
    val uvIndex: String = "",
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
data class WeatherDesc(
    val value: String = ""
) : Parcelable

@Parcelize
data class WeatherIconUrl(
    val value: String = ""
) : Parcelable