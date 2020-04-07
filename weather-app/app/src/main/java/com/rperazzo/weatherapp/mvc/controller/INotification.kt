package com.rperazzo.weatherapp.mvc.controller

import com.rperazzo.weatherapp.mvc.model.entity.City

interface INotification {

    fun notifyView (list: List <City>)

    fun notifyViewError()

}