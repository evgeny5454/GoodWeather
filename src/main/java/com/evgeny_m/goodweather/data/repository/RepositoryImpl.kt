package com.evgeny_m.goodweather.data.repository

import android.app.Activity
import com.evgeny_m.goodweather.R
import com.evgeny_m.goodweather.data.retrofit.GeocoderYandex
import com.evgeny_m.goodweather.data.retrofit.WeatherYandex
import com.evgeny_m.goodweather.domain.Repository
import com.evgeny_m.goodweather.domain.model.Weather

class RepositoryImpl(activity: Activity) : Repository {

    private val geocodeKey = activity.getString(R.string.api_key_yandex_geocode)
    private val weatherKey = activity.getString(R.string.api_key_yandex_weather)


    override suspend fun getGeocode(lat: Double, lon: Double): List<String> {
        val data = mutableListOf<String>()
        val response = GeocoderYandex.api.getGeocode(long_lat = "$lon,$lat", geocodeKey)

        val mapLocation = mutableMapOf<String, String>()
        response.body()?.response
            ?.GeoObjectCollection
            ?.featureMember?.first()
            ?.GeoObject?.metaDataProperty?.GeocoderMetaData?.Address?.Components?.forEach {
                if (it.kind == "locality") {
                    mapLocation[it.kind] = it.name
                } else if (it.name.contains("район")) {
                    mapLocation[it.kind] = it.name
                }
            }

        mapLocation.forEach { (key, value) ->
            if (key == "locality") {
                data.add(value)
            } else if (key == "district") {
                data.add(value)
            }
        }
        return data
    }

    override suspend fun getWeather(lat: Double, lon: Double): List<Weather> {
        val response = WeatherYandex.api.getWeather(weatherKey,lat, lon)
        val data = response.body()?.fact

        val list = mutableListOf<Weather>()
        val dataWeather = response.body()?.forecasts
        val weather = Weather(
            temp = data?.temp ?: 0.0,
            temp_feels_like = data?.feels_like ?: 0.0,
            icon = data?.icon ?: "",
            condition = data?.condition ?: "",
            pressure_mm = data?.pressure_mm ?: 0.0,
            humidity = data?.humidity ?: 0.0,
            daytime = data?.daytime ?: "",
            season = data?.season ?: "",
            date = dataWeather?.first()?.date ?:""
        )
        list.add(weather)

        dataWeather?.forEach { it ->
            val data1 = it.parts.day_short
            val weather2 = Weather(
                temp = data1.temp,
                temp_feels_like = data1.feels_like,
                icon = data1.icon,
                condition = data1.condition,
                pressure_mm = data1.pressure_mm,
                humidity = data1.humidity,
                daytime = data1.daytime,
                season = data?.season ?: "",
                date = it.date
            )
            list.add(weather2)
        }
        return list
    }
}


