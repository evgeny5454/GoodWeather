package com.evgeny_m.goodweather.data.model.weather_yandex

data class Tzinfo(
    val abbr: String,
    val dst: Boolean,
    val name: String,
    val offset: Int
)