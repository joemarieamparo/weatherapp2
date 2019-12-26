package com.example.weatherapp.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weatherapp.view.MainViewModel.Companion.FACTORY
import com.example.weatherapp.R
import com.example.weatherapp.data.WeatherRepo
import com.example.weatherapp.data.getDatabase
import com.example.weatherapp.data.getSearchApiService
import com.example.weatherapp.data.getWeatherApiService
import com.example.weatherapp.utils.addFragment
import com.example.weatherapp.utils.toastLongMsg
import com.example.weatherapp.view.fragments.cityweather.CityWeatherFragment
import com.example.weatherapp.view.fragments.searchresult.SearchResultFragment

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = WeatherRepo(getSearchApiService(), getWeatherApiService(), getDatabase(this).cityDao)
        viewModel = ViewModelProviders.of(this, FACTORY(repo)).get(MainViewModel::class.java)

        viewModel.loadCities()

        viewModel.searchCitiesLiveData.observe(this, Observer {
            it?.let {
                addFragment(SearchResultFragment.newInstance(it))
            }
        })

        viewModel.cityToForecastLiveData.observe(this, Observer {
            it?.let {
                viewModel.getWeather(it.lat, it.lon)
            }
        })

        viewModel.cityWeatherLiveData.observe(this, Observer {
            it?.let {
                searchView.onActionViewCollapsed()
                addFragment(CityWeatherFragment.newInstance(it))
                viewModel.saveCityInfoToDb(it.first)
            }
        })

        viewModel.toastMsg.observe(this, Observer {
            toastLongMsg(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.menu_search)
        searchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.enter_city)
        searchView.setOnQueryTextListener(object :  SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchCity(query)
                return false
            }
        })

        return true
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed()
            return false
        } else super.onKeyDown(keyCode, event)
    }
}
