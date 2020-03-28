package com.rperazzo.weatherapp.models;

import java.io.Serializable;

public class Main implements Serializable {
    public double temp;
    public double pressure;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
}
