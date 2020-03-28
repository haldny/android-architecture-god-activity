package com.rperazzo.weatherapp.models;

import java.util.List;

public class FindResult {
    public final List<City> list;

    public FindResult(List<City> list) {
        this.list = list;
    }
}
