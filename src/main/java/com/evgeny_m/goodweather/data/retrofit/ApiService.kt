package com.evgeny_m.goodweather.data.retrofit

import com.evgeny_m.goodweather.data.model.geocode_yandex.GeocodeResponse
import com.evgeny_m.goodweather.data.model.weather_yandex.WeatherResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("1.x/")
    suspend fun getGeocode(
        @Query("geocode") long_lat: String,
        @Query("apikey") apikey: String,
        @Query("kind") kind: String = "district",
        @Query("format") format: String = "json",
        @Query("results") results: Int = 1
    ): Response<GeocodeResponse>


    @GET("forecast")
    suspend fun getWeather(
        @Header("X-Yandex-API-Key") apikey: String,
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("lang") lang: String = "ru-Ru"
    ): Response<WeatherResponse>
}