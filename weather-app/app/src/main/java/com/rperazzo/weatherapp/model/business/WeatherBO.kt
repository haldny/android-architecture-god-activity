package com.rperazzo.weatherapp.model.business

import com.rperazzo.weatherapp.model.service.WeatherManager
import com.rperazzo.weatherapp.model.entity.City
import com.rperazzo.weatherapp.model.entity.FindResult
import com.rperazzo.weatherapp.model.service.WeatherService
import com.rperazzo.weatherapp.presenter.IPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherBO(private var presenter: IPresenter): Callback<FindResult> {
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
        presenter.updateModel(cities)
    }

    override fun onFailure(call: Call<FindResult>, t: Throwable) {
        presenter.notifyError();
    }

}