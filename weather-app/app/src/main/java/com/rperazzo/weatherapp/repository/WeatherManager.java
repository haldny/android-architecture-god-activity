package com.rperazzo.weatherapp.repository;

import com.rperazzo.weatherapp.model.City;
import com.rperazzo.weatherapp.model.Clouds;
import com.rperazzo.weatherapp.model.Main;
import com.rperazzo.weatherapp.model.Sys;
import com.rperazzo.weatherapp.model.Weather;
import com.rperazzo.weatherapp.model.Wind;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class WeatherManager {

    private static final String API_URL =
            "http://api.openweathermap.org/data/2.5/";
    public static final String API_KEY =
            "520d6b47a12735bee8f69c57737d145f";

    private static OkHttpClient mClient = new OkHttpClient();

    public static WeatherService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mClient)
                .build();

        return retrofit.create(WeatherService.class);
    }


}


