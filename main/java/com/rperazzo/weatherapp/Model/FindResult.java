package com.rperazzo.weatherapp.Model;

import java.util.List;

public class FindResult<T> {
    public final List<T> list;

    public FindResult(List<T> list) {
        this.list = list;
    }
}
