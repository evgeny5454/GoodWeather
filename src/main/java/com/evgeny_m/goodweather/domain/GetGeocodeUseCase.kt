package com.evgeny_m.goodweather.domain

class GetGeocodeUseCase(private val repository: Repository) {
    suspend fun execute(lat: Double, lon : Double) : List<String> {
        return repository.getGeocode(lat,lon)
    }
}