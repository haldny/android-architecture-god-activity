package com.rperazzo.weatherapp.MVVM.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.SharedPreferences
import com.rperazzo.weatherapp.MVVM.model.entity.FindResult
import com.rperazzo.weatherapp.MVVM.model.service.WeatherManager
import com.rperazzo.weatherapp.MVVM.model.service.WeatherService
import com.rperazzo.weatherapp.MVVM.utils.Constantes.*


class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private var sp: SharedPreferences? = null

    private var weatherService: WeatherService? = null

    private val weather = MutableLiveData<FindResult>()

    fun WeatherViewModel(application: Application) {
        weatherService = WeatherManager.getService()
        sp = application.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun find(): LiveData<FindResult>? {
        return weather
    }

    fun find(query: String?): LiveData<FindResult>? {
        /* val unit = getTemperatureUnit()
        object : AsyncTask<Void?, Void?, Void?>() {
            protected fun doInBackground(vararg voids: Void): Void? {
                try {
                    val result: FindResult? = query?.let {
                        weatherService
                                .find(it, unit, API_KEY)
                                .execute()
                                .body()
                    }
                    weather.postValue(result)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                return null
            }
        }.execute() */
        return weather
    }

    fun saveTemperatureUnit(newUnit: String?) = sp
        ?.edit()
        ?.putString(TEMPERATURE_UNIT_KEY, newUnit)
        ?.apply()

    fun getTemperatureUnit(): String {
        return sp!!.getString(TEMPERATURE_UNIT_KEY, "metric")
    }

}