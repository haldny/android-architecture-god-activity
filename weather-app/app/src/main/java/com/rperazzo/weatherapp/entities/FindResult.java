package com.rperazzo.weatherapp.entities;

import java.util.List;

public class FindResult {
    public final List<City> list;

    public FindResult(List<City> list) {
        this.list = list;
    }
}
