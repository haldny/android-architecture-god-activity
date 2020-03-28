package com.rperazzo.weatherapp.repository;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherManager implements IWeatherService{

    private static final String API_URL =
            "http://api.openweathermap.org/data/2.5/";
    public static final String API_KEY =
            "520d6b47a12735bee8f69c57737d145f";

    private static OkHttpClient mClient = new OkHttpClient();


    @Override
    public Call<FindResult> find(String cityName, String units, String apiKey) {
        return null;
    }

    @Override
    public IWeatherService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mClient)
                .build();

        return retrofit.create(IWeatherService.class);
    }
}
