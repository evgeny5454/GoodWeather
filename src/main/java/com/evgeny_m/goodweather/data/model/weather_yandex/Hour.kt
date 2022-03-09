package com.evgeny_m.goodweather.data.model.weather_yandex

data class Hour(
    val cloudness: Double,
    val condition: String,
    val feels_like: Double,
    val hour: String,
    val hour_ts: Double,
    val humidity: Double,
    val icon: String,
    val is_thunder: Boolean,
    val prec_mm: Double,
    val prec_period: Double,
    val prec_prob: Double,
    val prec_strength: Double,
    val prec_type: Double,
    val pressure_mm: Double,
    val pressure_pa: Double,
    val soil_moisture: Double,
    val soil_temp: Double,
    val temp: Double,
    val uv_index: Double,
    val wind_dir: String,
    val wind_gust: Double,
    val wind_speed: Double
)