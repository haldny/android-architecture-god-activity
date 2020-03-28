package com.rperazzo.weatherapp;

import android.view.View;

public class MainActivityPresenter {

    private View view;

    public MainActivityPresenter(View view) {
        this.view = view;
    }

    public void searchByName(String name) {
        view.searchByName(name);
    }

    public interface View{
        void searchByName(String name);
    }
}
