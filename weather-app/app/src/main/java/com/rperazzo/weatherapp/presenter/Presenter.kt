package com.rperazzo.weatherapp.presenter

import com.rperazzo.weatherapp.model.business.WeatherBO
import com.rperazzo.weatherapp.model.entity.City
import com.rperazzo.weatherapp.view.activity.IMainActivity

class Presenter(override val activity: IMainActivity) : IPresenter {

    private lateinit var weatherBO: WeatherBO
    var cities: List<City> = ArrayList<City>()

    init {
        weatherBO = WeatherBO(this)
    }

    override fun searchCityByName(search: String, units: String) {
        weatherBO.searchCityByName(search, units)
        notifyActivity()
    }

    override fun updateModel(cities: List<City>) {
        this.cities = cities
        notifyActivity()
    }

    private fun notifyActivity() {
        activity.updateResults(cities)
    }

    override fun notifyError() {
        activity.updateError()
    }
}