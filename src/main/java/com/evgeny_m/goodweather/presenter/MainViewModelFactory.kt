package com.evgeny_m.goodweather.presenter

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.evgeny_m.goodweather.data.repository.RepositoryImpl
import com.evgeny_m.goodweather.domain.GetGeocodeUseCase
import com.evgeny_m.goodweather.domain.GetWeatherUseCase

class MainViewModelFactory(private val activity: Activity) : ViewModelProvider.Factory {

    private val repository by lazy(LazyThreadSafetyMode.NONE) {
        RepositoryImpl(activity)
    }

    private val getWeatherUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetWeatherUseCase(repository)
    }
    private val getGeocodeUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetGeocodeUseCase(repository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
             getWeatherUseCase,
            getGeocodeUseCase
        ) as T
    }
}