package com.rperazzo.weatherapp.view.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rperazzo.weatherapp.R;
import com.rperazzo.weatherapp.model.City;
import com.rperazzo.weatherapp.viewmodel.WeatherViewModel;

import java.util.ArrayList;

public class FindItemAdapter extends ArrayAdapter<City> {

    WeatherViewModel viewModel;

    public FindItemAdapter(Context context, ArrayList<City> cities, WeatherViewModel viewModel) {
        super(context, 0, cities);
        this.viewModel = viewModel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final City city = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.city_list_item, parent, false);
        }

        TextView cityName = convertView.findViewById(R.id.cityNameTxt);
        TextView description = convertView.findViewById(R.id.descriptionTxt);
        TextView metric = convertView.findViewById(R.id.metricTxt);
        TextView temp = convertView.findViewById(R.id.tempTxt);
        TextView wind = convertView.findViewById(R.id.windTxt);
        TextView clouds = convertView.findViewById(R.id.cloudsTxt);
        TextView pressure = convertView.findViewById(R.id.pressureTxt);
        ImageView icon = convertView.findViewById(R.id.weatherIcon);

        cityName.setText(city.getTitle());
        description.setText(city.getDescription());
        temp.setText(city.getTemperature());
        clouds.setText(city.getClouds());
        pressure.setText(city.getPressure());

        String units = viewModel.getTemperatureUnit();
        if ("metric".equals(units)) {
            metric.setText("ºC");
            wind.setText(city.getWind() + " m/s");
        } else {
            metric.setText("ºF");
            wind.setText(city.getWind() + " m/h");
        }

        int resId = getContext().getResources().getIdentifier(
            "w_"+city.weather.get(0).icon,
            "drawable",
            getContext().getPackageName()
        );

        icon.setImageResource(resId);

        return convertView;
    }

}