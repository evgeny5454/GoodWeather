package com.evgeny_m.goodweather.data.model.geocode_yandex

data class GeocoderResponseMetaData(
    val Point: PointX,
    val found: String,
    val request: String,
    val results: String
)