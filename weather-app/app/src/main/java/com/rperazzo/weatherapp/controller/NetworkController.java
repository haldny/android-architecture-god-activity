package com.rperazzo.weatherapp.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkController {
    private Context context;
    public NetworkController(Context context){
        this.context = context;
    }

    public boolean isDeviceConnected() {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(this.context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
