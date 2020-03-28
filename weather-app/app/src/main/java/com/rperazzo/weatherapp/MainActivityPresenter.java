package com.rperazzo.weatherapp;

public class MainActivityPresenter {

    private View view;

    public MainActivityPresenter(View view) {
        this.view = view;
    }

    public void searchByName(String name) {
        view.searchByName(name);
    }

    public void cleanList() {
        view.cleanList();
    }

    public interface View{
        void searchByName(String name);
        void cleanList();
    }
}
