package com.evgeny_m.goodweather.presenter

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.evgeny_m.goodweather.R
import com.evgeny_m.goodweather.databinding.FragmentMainWeatherBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MainWeatherFragment : Fragment() {

    private lateinit var binding: FragmentMainWeatherBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainWeatherBinding.inflate(layoutInflater)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())


        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(requireActivity())
        )[MainViewModel::class.java]

        mainViewModel.geocode.observe(viewLifecycleOwner, Observer { geocode ->
            binding.city.text = geocode[0]
            binding.county.text = geocode[1]
        })

       /* mainViewModel.weather.observe(viewLifecycleOwner, Observer { weather->
            binding.temp.text = "${weather.temp}°C"
            binding.tempFeels.text = "ощущается ${weather.temp_feels_like}"
            binding.humidity.text = "влажность ${weather.humidity}%"

            val url: Uri = "https://yastatic.net/weather/i/icons/funky/dark/${weather.icon}.svg".toUri()

            GlideToVectorYou.justLoadImage(requireActivity(), url, binding.image)

        })*/



        return binding.root
    }

    @SuppressLint("MissingPermission")
    fun updateGps() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            mainViewModel.getGeocode(it.latitude, it.longitude)
            mainViewModel.getWeather(it.latitude, it.longitude)
            Log.d("LAST_LOCATION" , "lat = ${it.latitude}, long = ${it.longitude}")
            //mainViewModel.getGeocode(55.78378378378378, 37.48964472033613)
        }
    }

    override fun onResume() {
        super.onResume()
        updateGps()
    }
}