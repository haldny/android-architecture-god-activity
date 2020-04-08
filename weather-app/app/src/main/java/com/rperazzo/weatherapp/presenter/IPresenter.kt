package com.rperazzo.weatherapp.presenter

import com.rperazzo.weatherapp.model.entity.City
import com.rperazzo.weatherapp.view.activity.IMainActivity

interface IPresenter {
    val activity: IMainActivity

    fun searchCityByName(search: String, units: String)
    fun updateModel(cities: List<City>)
    fun notifyError()
}