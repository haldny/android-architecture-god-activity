package com.rperazzo.weatherapp.Model;

import com.rperazzo.weatherapp.Controller.WeatherController;

import org.junit.Assert;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

public class WeatherServiceTest {

    @Test
    public void find() {
        WeatherController controller = new WeatherController(OpenWeatherService.getService());
        String cityName = "Recife";
        String unit = "metric";
        String apikey = OpenWeatherService.API_KEY;

        final Call<FindResult<City>> findCall = controller.GetCities(cityName, unit, OpenWeatherService.API_KEY);
        findCall.enqueue(new Callback<FindResult<City>>() {
            @Override
            public void onResponse(Call<FindResult<City>> call, Response<FindResult<City>> response) {
                assert response.body() != null;
                assertFalse(response.body().list.isEmpty());
            }

            @Override
            public void onFailure(Call<FindResult<City>> call, Throwable t) {
                Assert.fail();
            }
        });
    }
}