package com.rperazzo.weatherapp.mvc.model.service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


object WeatherManager {

    private val API_URL:String = "http://api.openweathermap.org/data/2.5/"
    public final val API_KEY: String = "4b878ebcce5d32e9b5860fb966909b57"
    private val mClient: OkHttpClient = OkHttpClient();

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mClient)
            .build();

    fun getService() = retrofit.create(WeatherService::class.java)
}
