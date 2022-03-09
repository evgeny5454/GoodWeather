package com.evgeny_m.goodweather.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evgeny_m.goodweather.domain.GetGeocodeUseCase
import com.evgeny_m.goodweather.domain.GetWeatherUseCase
import com.evgeny_m.goodweather.domain.model.Weather
import kotlinx.coroutines.launch

class MainViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getGeocodeUseCase: GetGeocodeUseCase
) : ViewModel() {

    private val mutableWeather: MutableLiveData<List<Weather>> = MutableLiveData()
    val weather: LiveData<List<Weather>> = mutableWeather

    private val mutableGeocode: MutableLiveData<List<String>> = MutableLiveData()
    val geocode: LiveData<List<String>> = mutableGeocode

    fun getWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            mutableWeather.value = getWeatherUseCase.execute(lat, lon)
        }
    }

    fun getGeocode(lat: Double, lon: Double) {
        viewModelScope.launch {
            mutableGeocode.value = getGeocodeUseCase.execute(lat, lon)
        }
    }
}