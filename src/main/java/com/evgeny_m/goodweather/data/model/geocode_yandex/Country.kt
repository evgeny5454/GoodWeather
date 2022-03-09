package com.evgeny_m.goodweather.data.model.geocode_yandex

data class Country(
    val AddressLine: String,
    val AdministrativeArea: AdministrativeArea,
    val CountryName: String,
    val CountryNameCode: String
)