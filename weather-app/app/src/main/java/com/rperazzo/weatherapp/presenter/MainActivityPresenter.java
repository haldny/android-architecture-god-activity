package com.rperazzo.weatherapp.presenter;

import android.content.Context;

public interface MainActivityPresenter {
    void onCreate();
    void initializeLocationModel(Context context);
    void updateUnitIfNecessary(String newUnits);
    void searchByName();
}
