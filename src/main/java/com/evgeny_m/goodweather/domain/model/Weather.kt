package com.evgeny_m.goodweather.domain.model

data class Weather(
    val temp: Double,
    val temp_feels_like: Double,
    val icon: String,
    val condition: String,
    val pressure_mm: Double,
    val humidity: Double,
    val daytime: String,
    val season: String,
    val date: String
)
