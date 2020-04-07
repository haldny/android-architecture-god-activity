package com.rperazzo.weatherapp.mvc.model.business

import com.rperazzo.weatherapp.mvc.controller.INotification
import com.rperazzo.weatherapp.mvc.model.service.WeatherManager
import com.rperazzo.weatherapp.mvc.model.entity.City
import com.rperazzo.weatherapp.mvc.model.entity.FindResult
import com.rperazzo.weatherapp.mvc.model.service.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherBO(private val notification: INotification): Callback<FindResult> {
    private val cities = ArrayList<City>()

    fun searchCityByName(search: String, units: String){
        val wService: WeatherService = WeatherManager.getService()
        val findCall: Call<FindResult> = wService.find(search, units, WeatherManager.API_KEY)
        findCall.enqueue(this)
    }

    override fun onResponse(call: Call<FindResult>, response: Response<FindResult>) {
        cities.clear()
        response.body()?.let{
            cities.addAll(it.list)
        }
        notification.notifyView(cities)
    }

    override fun onFailure(call: Call<FindResult>, t: Throwable) {
        notification.notifyViewError();
    }

}