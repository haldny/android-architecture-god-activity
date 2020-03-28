package com.rperazzo.weatherapp.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.rperazzo.weatherapp.model.City;
import com.rperazzo.weatherapp.model.LocationModel;
import com.rperazzo.weatherapp.repository.FindResult;
import com.rperazzo.weatherapp.repository.WeatherManager;
import com.rperazzo.weatherapp.repository.WeatherService;
import com.rperazzo.weatherapp.view.MainActivityView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenterImpl implements MainActivityPresenter {

    private MainActivityView view;
    private ArrayList<City> cities = new ArrayList<>();
    private LocationModel locationModel;

    public MainActivityPresenterImpl(MainActivityView view){
        this.view = view;
    }

    @Override
    public void onCreate() {
        this.view.setupAdapter(cities);
    }

    @Override
    public void initializeLocationModel(Context context){
        this.locationModel = new LocationModel(context);
    }

    @Override
    public void updateUnitIfNecessary(String newUnits) {
        String currentUnits = LocationModel.getTemperatureUnit();
        if (!currentUnits.equals(newUnits)) {
            LocationModel.setTemperatureUnit(newUnits);
            searchByName();
        }
    }

    @Override
    public void searchByName() {
        if (!isDeviceConnected()) {
            view.showToast("No connection!");
            return;
        }

        String search = view.getSearch();
        if (TextUtils.isEmpty(search)) {
            return;
        }

        view.onStartLoading();

        WeatherService wService = WeatherManager.getService();
        String units = LocationModel.getTemperatureUnit();
        final Call<FindResult> findCall = wService.find(search, units, WeatherManager.API_KEY);
        findCall.enqueue(new Callback<FindResult>() {
            @Override
            public void onResponse(Call<FindResult> call, Response<FindResult> response) {
                cities.clear();
                FindResult result = response.body();
                if (result != null) {
                    cities.addAll(result.list);
                    view.onFinishLoading(response.body());
                } else {
                    view.onFinishLoadingWithoutResults();
                }
            }

            @Override
            public void onFailure(Call<FindResult> call, Throwable t) {
                view.onFinishLoadingWithError();
            }
        });
    }

    private boolean isDeviceConnected() {
        ConnectivityManager cm = view.returnSystemService();
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
