package com.evgeny_m.goodweather.data.model.weather_yandex

data class WeatherResponse(
    val fact: Fact,
    val forecasts: List<Forecast>,
    val geo_object: GeoObject,
    val info: Info,
    val now: Int,
    val now_dt: String,
    val yesterday: Yesterday
)