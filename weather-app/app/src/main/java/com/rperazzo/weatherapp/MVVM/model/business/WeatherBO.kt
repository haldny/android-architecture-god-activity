package com.rperazzo.weatherapp.MVVM.model.business

import com.rperazzo.weatherapp.MVVM.model.service.WeatherManager
import com.rperazzo.weatherapp.MVVM.model.entity.City
import com.rperazzo.weatherapp.MVVM.model.entity.FindResult
import com.rperazzo.weatherapp.MVVM.model.service.WeatherService
import com.rperazzo.weatherapp.MVVM.utils.Constantes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherBO(): Callback<FindResult> {
    private val cities = ArrayList<City>()

    fun searchCityByName(search: String, units: String){
        val wService: WeatherService = WeatherManager.getService()
        val findCall: Call<FindResult> = wService.find(search, units, Constantes.API_KEY)
        findCall.enqueue(this)
    }

    override fun onResponse(call: Call<FindResult>, response: Response<FindResult>) {
        cities.clear()
        response.body()?.let{
            cities.addAll(it.list)
        }
    }

    override fun onFailure(call: Call<FindResult>, t: Throwable) {
    }

}