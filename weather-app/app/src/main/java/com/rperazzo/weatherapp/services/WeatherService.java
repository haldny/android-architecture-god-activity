package com.rperazzo.weatherapp.services;

import com.rperazzo.weatherapp.model.FindResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("find")
    Call<FindResult> find(
            @Query("q") String cityName,
            @Query("units") String units,
            @Query("appid") String apiKey
    );

}
