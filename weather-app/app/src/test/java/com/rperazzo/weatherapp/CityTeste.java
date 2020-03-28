package com.rperazzo.weatherapp;

import org.junit.Assert;
import com.rperazzo.weatherapp.entities.City;
import com.rperazzo.weatherapp.entities.Main;

import org.junit.Test;

public class CityTeste {
    @Test
    public void getPressure() {
        City city = new City();
        Main main = new Main();
        main.setPressure(200.0);

        city.setMain(main);

        Assert.assertNotNull(city.getPressure());
    }
}
