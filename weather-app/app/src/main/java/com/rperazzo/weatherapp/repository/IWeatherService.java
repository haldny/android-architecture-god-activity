package com.rperazzo.weatherapp.repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IWeatherService {

    @GET("find")
    Call<FindResult> find(
            @Query("q") String cityName,
            @Query("units") String units,
            @Query("appid") String apiKey
    );

    IWeatherService getService();
}
