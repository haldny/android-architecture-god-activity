package com.rperazzo.weatherapp.repository;

import com.rperazzo.weatherapp.model.City;

import java.util.List;

public class FindResult {
    public final List<City> list;

    public FindResult(List<City> list) {
        this.list = list;
    }
}
