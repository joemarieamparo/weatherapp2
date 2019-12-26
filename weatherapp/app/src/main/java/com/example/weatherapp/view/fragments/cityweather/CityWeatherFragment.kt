package com.example.weatherapp.view.fragments.cityweather

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.City
import com.example.weatherapp.data.models.Weather
import com.example.weatherapp.utils.string
import kotlinx.android.synthetic.main.fragment_city_weather.view.*

class CityWeatherFragment : Fragment() {

    companion object {

        const val EXTRA_CITY_INFO = "EXTRA_CITY_INFO"
        const val EXTRA_CITY_WEATHER_CONDITION = "EXTRA_CITY_WEATHER_INFO"

        fun newInstance(cityWeather: Pair<City, List<Weather>>): CityWeatherFragment {
            var fragment = CityWeatherFragment()
            var bundle = Bundle()
            bundle.putParcelable(EXTRA_CITY_INFO, cityWeather.first)
            bundle.putParcelableArrayList(EXTRA_CITY_WEATHER_CONDITION, ArrayList<Parcelable>(cityWeather.second))
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val v =  inflater.inflate(R.layout.fragment_city_weather, container, false)

        val city = arguments!!.getParcelable<City>(EXTRA_CITY_INFO)!!

        //TODO: Handle to view
        val cityWeatherConditions =
            arguments!!.getParcelableArrayList<Weather>(EXTRA_CITY_WEATHER_CONDITION)!!

        Glide.with(this)
            .load(city.weatherIconUrl)
            .into(v.imageview_weather_icon)

        v.textview_city_name.string("${city.area}, " +
                "${city.region} " +
                "\n${city.country}")

        v.textview_humidity.string("${city.humidity}% humidity")
        v.textview_current_weather.string(city.weatherDesc)
        v.textview_temp.string("${city.temp} degree Celsius")



        return v
    }
}