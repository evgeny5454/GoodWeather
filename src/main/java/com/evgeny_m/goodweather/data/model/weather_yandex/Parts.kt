package com.evgeny_m.goodweather.data.model.weather_yandex

data class Parts(
    val day: Day,
    val day_short: DayShort,
    val evening: Evening,
    val morning: Morning,
    val night: Night,
    val night_short: NightShort
)