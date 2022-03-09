package com.evgeny_m.goodweather.data.model.weather_yandex

data class GeoObject(
    val country: Country,
    val district: District,
    val locality: Locality,
    val province: Province
)