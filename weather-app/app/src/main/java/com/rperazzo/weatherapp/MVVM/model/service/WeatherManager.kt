package com.rperazzo.weatherapp.MVVM.model.service;

import com.rperazzo.weatherapp.MVVM.utils.Constantes
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

object WeatherManager {

    private val mClient: OkHttpClient = OkHttpClient();

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constantes.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mClient)
            .build();

    fun getService() = retrofit.create(WeatherService::class.java)
}
