package com.example.weatherapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResult(
    val search_api: SearchApi = SearchApi()
) : Parcelable

@Parcelize
data class SearchApi(
    @SerializedName("result")
    val result: List<ResultInfo> = listOf()
) : Parcelable

@Parcelize
data class ResultInfo(
    val areaName: List<AreaName> = listOf(),
    val country: List<Country> = listOf(),
    val latitude: String = "",
    val longitude: String = "",
    val population: String = "",
    val region: List<Region> = listOf(),
    val weatherUrl: List<WeatherUrl> = listOf()
) : Parcelable

@Parcelize
data class AreaName(
    val value: String = ""
) : Parcelable

@Parcelize
data class Country(
    val value: String = ""
) : Parcelable

@Parcelize
data class Region(
    val value: String = ""
) : Parcelable

@Parcelize
data class WeatherUrl(
    val value: String = ""
) : Parcelable