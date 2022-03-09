package com.evgeny_m.goodweather.data.model.weather_yandex

data class Morning(
    val _source: String,
    val cloudness: Double,
    val condition: String,
    val daytime: String,
    val feels_like: Double,
    val fresh_snow_mm: Double,
    val humidity: Double,
    val icon: String,
    val polar: Boolean,
    val prec_mm: Double,
    val prec_period: Double,
    val prec_prob: Double,
    val prec_strength: Double,
    val prec_type: Double,
    val pressure_mm: Double,
    val pressure_pa: Double,
    val soil_moisture: Double,
    val soil_temp: Double,
    val temp_avg: Double,
    val temp_max: Double,
    val temp_min: Double,
    val uv_index: Double,
    val wind_dir: String,
    val wind_gust: Double,
    val wind_speed: Double
)