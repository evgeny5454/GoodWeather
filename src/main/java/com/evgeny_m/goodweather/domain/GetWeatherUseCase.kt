package com.evgeny_m.goodweather.domain

import com.evgeny_m.goodweather.domain.model.Weather

class GetWeatherUseCase(private val repository: Repository) {

    suspend fun execute(lat: Double, lon: Double): List<Weather> {
        return repository.getWeather(lat, lon)
    }
}