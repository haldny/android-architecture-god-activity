package com.rperazzo.weatherapp.mvc.controller

import com.rperazzo.weatherapp.mvc.model.business.WeatherBO


class Controller(private val notification: INotification) {

    private var weatherBO: WeatherBO = WeatherBO(notification)


    fun searchCityByName(search: String, units: String) {
        weatherBO.searchCityByName(search, units)
    }

}