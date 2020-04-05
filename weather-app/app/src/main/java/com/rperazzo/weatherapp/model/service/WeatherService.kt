package com.rperazzo.weatherapp.model.service

import com.rperazzo.weatherapp.model.entity.FindResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface WeatherService {
    @GET("find")
    fun find(
            @Query("q") cityName: String,
            @Query("units") units: String,
            @Query("appid") apiKey: String
    ): Call<FindResult>;
}
