package com.rperazzo.weatherapp.controller;

import android.content.Context;

import com.rperazzo.weatherapp.repository.FindResult;
import com.rperazzo.weatherapp.repository.WeatherManager;
import com.rperazzo.weatherapp.view.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {
    private MainActivity mainActivity;

    public MainController(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void searchByName(String cityName, String unit){

        WeatherManager wService = new WeatherManager();
        final Call<FindResult> findCall = wService.getService().find(cityName, unit, WeatherManager.API_KEY);
        findCall.enqueue(new Callback<FindResult>() {
            @Override
            public void onResponse(Call<FindResult> call, Response<FindResult> response) {
                mainActivity.searchResponse(true ,response.body());
            }

            @Override
            public void onFailure(Call<FindResult> call, Throwable t) {
                mainActivity.searchResponse(false , null);
            }
        });
    }
}
