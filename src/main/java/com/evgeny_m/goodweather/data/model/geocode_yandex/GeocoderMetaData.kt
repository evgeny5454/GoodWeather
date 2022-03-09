package com.evgeny_m.goodweather.data.model.geocode_yandex

data class GeocoderMetaData(
    val Address: Address,
    val AddressDetails: AddressDetails,
    val kind: String,
    val precision: String,
    val text: String
)