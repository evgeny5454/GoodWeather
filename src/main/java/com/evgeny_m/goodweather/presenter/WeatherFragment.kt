package com.evgeny_m.goodweather.presenter

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny_m.goodweather.databinding.FragmentWeatherBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs


class WeatherFragment : Fragment(), AppBarLayout.OnOffsetChangedListener {

    private var mIsTheTitleVisible = false
    private var mIsTheTitleContainerVisible = true

    private lateinit var binding: FragmentWeatherBinding
    private lateinit var mTitleContainer: LinearLayout
    private lateinit var toolbar: Toolbar

    private lateinit var mainViewModel: MainViewModel
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(layoutInflater)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())


        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(requireActivity())
        )[MainViewModel::class.java]

        binding.appBarLayout.addOnOffsetChangedListener(this)
        mTitleContainer = binding.mainLinearlayoutTitle
        toolbar = binding.toolbar
        toolbar.visibility = View.INVISIBLE


        val recyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(requireContext())
        val adapter = WeatherAdapter(requireActivity())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        mainViewModel.geocode.observe(viewLifecycleOwner, { geocode ->
            binding.city.text = geocode[0]
            binding.county.text = geocode[1]
        })

        mainViewModel.weather.observe(viewLifecycleOwner, { weather ->

            val temp = weather.first().temp.toInt()
            val feelsTemp = weather.first().temp_feels_like.toInt()
            if (temp > 0) {
                toolbar.title = "+${temp}°C"
                binding.temp.text = "+${temp}°C"
            } else {
                toolbar.title = "${temp}°C"
                binding.temp.text = "$temp°C"
            }
            binding.description.text =
                getWeatherDescription(requireContext(), weather.first().condition)
            binding.tempFeels.text = "ощущается $feelsTemp"

            val url: Uri =
                "https://yastatic.net/weather/i/icons/funky/dark/${weather.first().icon}.svg".toUri()
            GlideToVectorYou.justLoadImage(requireActivity(), url, binding.iconWeather)

            loadImage(binding.imageView, requireContext(), weather.first().condition)

            adapter.addItems(weather, weather.first().date)
        })
        return binding.root
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val maxScroll: Int? = appBarLayout?.totalScrollRange
        val percentage = abs(verticalOffset).toFloat() / maxScroll?.toFloat()!!
        handleAlphaOnTitle(percentage)
        handleToolbarTitleVisibility(percentage)
    }

    private fun handleToolbarTitleVisibility(percentage: Float) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(toolbar, ALPHA_ANIMATIONS_DURATION.toLong(), View.VISIBLE)
                mIsTheTitleVisible = true
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(toolbar, ALPHA_ANIMATIONS_DURATION.toLong(), View.INVISIBLE)
                mIsTheTitleVisible = false
            }
        }
    }

    private fun handleAlphaOnTitle(percentage: Float) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(
                    mTitleContainer,
                    ALPHA_ANIMATIONS_DURATION.toLong(),
                    View.INVISIBLE
                )
                mIsTheTitleContainerVisible = false
            }
        } else {
            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(
                    mTitleContainer,
                    ALPHA_ANIMATIONS_DURATION.toLong(),
                    View.VISIBLE
                )
                mIsTheTitleContainerVisible = true
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun updateGps() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            mainViewModel.getGeocode(it.latitude, it.longitude)
            mainViewModel.getWeather(it.latitude, it.longitude)
            Log.d("LAST_LOCATION", "lat = ${it.latitude}, long = ${it.longitude}")
        }
    }

    override fun onResume() {
        super.onResume()
        updateGps()
    }

    companion object {
        private const val PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f
        private const val PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f
        private const val ALPHA_ANIMATIONS_DURATION = 200
        fun startAlphaAnimation(v: View, duration: Long, visibility: Int) {
            val alphaAnimation =
                if (visibility == View.VISIBLE) AlphaAnimation(0f, 1f) else AlphaAnimation(1f, 0f)
            alphaAnimation.duration = duration
            alphaAnimation.fillAfter = true
            v.startAnimation(alphaAnimation)
        }
    }
}
