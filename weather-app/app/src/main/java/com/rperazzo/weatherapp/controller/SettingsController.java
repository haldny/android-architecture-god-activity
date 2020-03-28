package com.rperazzo.weatherapp.controller;

import android.content.Context;

import com.rperazzo.weatherapp.entity.Preferences;

public class SettingsController {
    private Preferences preferences;

    public SettingsController(Context context){
        this.preferences = new Preferences(context);
    }

    public void setTemperatureUnit(String unit){
        preferences.setTemperatureUnit(unit);
    }

}
