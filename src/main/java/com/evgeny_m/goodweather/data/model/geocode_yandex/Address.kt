package com.evgeny_m.goodweather.data.model.geocode_yandex

data class Address(
    val Components: List<Component>,
    val country_code: String,
    val formatted: String
)