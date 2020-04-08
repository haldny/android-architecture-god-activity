package com.rperazzo.weatherapp.mvc.model.entity

import org.junit.Assert.*

import org.junit.Test

class MainTest {

    @Test
    fun void testTemp() {
        val main = Main()
        main.temp(1.0)
        assertEquals(
                1.0,
                main.temp()
        )
    }
}