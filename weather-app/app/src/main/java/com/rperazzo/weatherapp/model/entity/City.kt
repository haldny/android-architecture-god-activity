package com.rperazzo.weatherapp.model.entity

import java.text.DecimalFormat

data class City(
        val id: Int,
        val name: String,
        val sys: Sys,
        val main : Main,
        val wind: Wind,
        val clouds: Clouds,
        val weather: List<Weather>){

    public fun getTitle(): String{
        return this.name + ", " + this.sys.country.toUpperCase();
    }

    public fun getPressure(): String{
        return DecimalFormat("#").format(this.main.pressure) + " hpa";
    }

    public fun getWind(): String{
        return "wind " + DecimalFormat("#.#").format(this.wind.speed);
    }

    public fun getClouds(): String{
        return "clouds " + this.clouds.all + "%";
    }

    public fun getTemperature(): String{
        return DecimalFormat("#").format(this.main.temp);
    }

    public fun getDescription(): String{
        return this.weather.get(0).description;
    }
}