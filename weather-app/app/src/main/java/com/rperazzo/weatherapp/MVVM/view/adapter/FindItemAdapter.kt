package com.rperazzo.weatherapp.MVVM.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.rperazzo.weatherapp.R
import com.rperazzo.weatherapp.MVVM.model.entity.City
import com.rperazzo.weatherapp.MVVM.view.activity.MainActivity

class FindItemAdapter(context: Context, val cities: ArrayList<City>): ArrayAdapter<City>(context, 0, cities) {

    @Override
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View{
        var viewItem :View;
        val city: City = getItem(position);

        if (convertView == null) {
            viewItem = LayoutInflater.from(getContext())
                    .inflate(R.layout.city_list_item, parent, false);
        }else{
            viewItem = convertView;
        }
        val cityName: TextView = viewItem.findViewById(R.id.cityNameTxt);
        cityName.setText(city.getTitle());

        val description: TextView = viewItem.findViewById(R.id.descriptionTxt);
        description.setText(city.getDescription());

        val metric: TextView = viewItem.findViewById(R.id.metricTxt);
        val units: String = (context as MainActivity).getTemperatureUnit()
        if ("metric".equals(units)) {
            metric.setText("ºC");
        } else {
            metric.setText("ºF");
        }

        val temp: TextView = viewItem.findViewById(R.id.tempTxt);
        temp.setText(city.getTemperature());

        val wind: TextView = viewItem.findViewById(R.id.windTxt);
        if ("metric".equals(units)) {
            wind.setText(city.getWind() + " m/s");
        } else {
            wind.setText(city.getWind() + " m/h");
        }

        val clouds: TextView = viewItem.findViewById(R.id.cloudsTxt);
        clouds.setText(city.getClouds());

        val pressure : TextView = viewItem.findViewById(R.id.pressureTxt);
        pressure.setText(city.getPressure());

        val resId: Int = getContext().getResources().getIdentifier(
                "w_"+city.weather.get(0).icon,
                "drawable",
                getContext().getPackageName());

        val icon: ImageView = viewItem.findViewById(R.id.weatherIcon);
        icon.setImageResource(resId);

        return viewItem;
    }
}