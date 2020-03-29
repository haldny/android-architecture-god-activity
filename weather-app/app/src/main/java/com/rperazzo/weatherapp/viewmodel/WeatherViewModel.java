package com.rperazzo.weatherapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.rperazzo.weatherapp.services.WeatherService;
import com.rperazzo.weatherapp.utils.WeatherManager;
import com.rperazzo.weatherapp.model.FindResult;
import com.rperazzo.weatherapp.utils.Const;

import java.io.IOException;

public class WeatherViewModel extends AndroidViewModel {

    private SharedPreferences sp;

    private WeatherService weatherService;

    private MutableLiveData<FindResult> weather = new MutableLiveData<FindResult>();

    public WeatherViewModel(@NonNull Application application) {
        super(application);

        this.weatherService = WeatherManager.getService();
        this.sp = application.getSharedPreferences(Const.PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public LiveData<FindResult> find() {
        return weather;
    }

    public LiveData<FindResult> find(final String query) {
        final String unit = getTemperatureUnit();

        AsyncTask async = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {

                try {

                    FindResult result = weatherService
                            .find(query, unit, Const.APP_KEY)
                            .execute()
                            .body();

                    weather.postValue(result);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

        }.execute();

        return weather;
    }

    public void saveTemperatureUnit(String newUnit) {
        sp
                .edit()
                .putString(Const.TEMPERATURE_UNIT_KEY, newUnit)
                .apply();
    }

    public String getTemperatureUnit() {
        return sp.getString(Const.TEMPERATURE_UNIT_KEY, "metric");
    }

}
