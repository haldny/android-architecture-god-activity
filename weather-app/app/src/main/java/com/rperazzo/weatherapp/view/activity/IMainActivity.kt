package com.rperazzo.weatherapp.view.activity

import com.rperazzo.weatherapp.model.entity.City

interface IMainActivity {
    fun updateResults(cities: List<City>)
    fun updateError()
}