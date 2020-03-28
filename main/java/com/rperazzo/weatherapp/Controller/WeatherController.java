package com.rperazzo.weatherapp.Controller;

import com.rperazzo.weatherapp.Model.City;
import com.rperazzo.weatherapp.Model.FindResult;
import com.rperazzo.weatherapp.Model.WeatherService;

import retrofit2.Call;

public class WeatherController {

    private WeatherService service;

    public WeatherController(WeatherService service){
        this.service = service;
    }
    public Call<FindResult<City>> GetCities(String cityName, String unit, String apiKey){
        return service.find(cityName, unit, apiKey);
    }
}
