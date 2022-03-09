package com.evgeny_m.goodweather.domain

import com.evgeny_m.goodweather.domain.model.Weather

interface Repository {
    suspend fun getGeocode(lat: Double, lon: Double): List<String>
    suspend fun getWeather(lat: Double, lon: Double): List<Weather>
}