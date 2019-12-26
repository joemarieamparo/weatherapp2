package com.example.weatherapp.utils

import android.app.Activity
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.data.City
import com.example.weatherapp.data.models.CurrentCondition
import com.example.weatherapp.data.models.ResultInfo

fun AppCompatActivity.addFragment(fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.container, fragment)
    transaction.addToBackStack(null)
    transaction.commit()
}

fun TextView.string(string: String?) {
    string?.let { this.text = string }
}

fun Activity.toastLongMsg(stringId: Int) {
    Toast.makeText(this, getString(stringId), Toast.LENGTH_LONG).show()
}

fun ResultInfo.cityInfo(list: List<CurrentCondition>? = null): City {
    var city = City()
    city.area = this.areaName[0].value
    city.region = this.region[0].value
    city.country = this.country[0].value
    city.lat = latitude
    city.lon = longitude
    list?.let {
        city.weatherIconUrl = it[0].weatherIconUrl[0].value
        city.weatherDesc = it[0].weatherDesc[0].value
        city.humidity = it[0].humidity
        city.temp = it[0].temp_C
    }

    return city
}