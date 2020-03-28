package com.rperazzo.weatherapp.entity;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private static final String PREFERENCE_NAME = "com.rperazzo.weatherapp.shared";
    private static final String TEMPERATURE_UNIT_KEY = "TEMPERATURE_UNIT_KEY";
    private SharedPreferences mSharedPref;

    public Preferences(Context context){
        this.mSharedPref = context.getSharedPreferences(PREFERENCE_NAME, context.MODE_PRIVATE);
    }

    public void setTemperatureUnit(String value) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(TEMPERATURE_UNIT_KEY, value);
        editor.apply();
    }

    public String getTemperatureUnit() {
        return mSharedPref.getString(TEMPERATURE_UNIT_KEY, "metric");
    }
}
