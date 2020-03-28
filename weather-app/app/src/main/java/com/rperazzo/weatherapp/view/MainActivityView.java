package com.rperazzo.weatherapp.view;

import android.net.ConnectivityManager;

import com.rperazzo.weatherapp.model.City;
import com.rperazzo.weatherapp.repository.FindResult;

import java.util.ArrayList;

public interface MainActivityView {
    void setupAdapter(ArrayList<City> cities);
    ConnectivityManager returnSystemService();
    void showToast(String message);
    String getSearch();
    void onStartLoading();
    void onFinishLoading(FindResult result);
    void onFinishLoadingWithoutResults();
    void onFinishLoadingWithError();
}
