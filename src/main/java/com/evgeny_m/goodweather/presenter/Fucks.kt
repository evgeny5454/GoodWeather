package com.evgeny_m.goodweather.presenter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.evgeny_m.goodweather.R

fun getWeatherDescription(context: Context,condition: String): String {
    return when (condition) {
        "clear" -> {
            context.getString(R.string.clear)
        }
        "partly-cloudy" -> {
            context.getString(R.string.partly_cloudy)
        }
        "cloudy" -> {
            context.getString(R.string.cloudy)
        }
        "overcast" -> {
            context.getString(R.string.overcast)
        }
        "drizzle" -> {
            context.getString(R.string.drizzle)
        }
        "light-rain" -> {
            context.getString(R.string.light_rain)
        }
        "rain" -> {
            context.getString(R.string.rain)
        }
        "moderate-rain" -> {
            context.getString(R.string.moderate_rain)
        }
        "heavy-rain" -> {
            context.getString(R.string.heavy_rain)
        }
        "continuous-heavy-rain" -> {
            context.getString(R.string.continuous_heavy_rain)
        }
        "showers" -> {
            context.getString(R.string.showers)
        }
        "wet-snow" -> {
            context.getString(R.string.wet_snow)
        }
        "light-snow" -> {
            context.getString(R.string.light_snow)
        }
        "snow" -> {
            context.getString(R.string.snow)
        }
        "snow-showers" -> {
            context.getString(R.string.snow_showers)
        }
        "hail" -> {
            context.getString(R.string.hail)
        }
        "thunderstorm" -> {
            context.getString(R.string.thunderstorm)
        }
        "thunderstorm-with-rain" -> {
            context.getString(R.string.thunderstorm_with_rain)
        }
        "thunderstorm-with-hail" -> {
            context.getString(R.string.thunderstorm_with_hail)
        }
        else -> ""
    }
}
fun loadImage(view: ImageView, context: Context, condition: String  ) {

     when (condition) {
        "clear" -> {
            Glide.with(context)
                .load(R.drawable.clear)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "partly-cloudy" -> {
            Glide.with(context)
                .load(R.drawable.partly_cloudy)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "cloudy" -> {
            Glide.with(context)
                .load(R.drawable.cloudy)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "overcast" -> {
            Glide.with(context)
                .load(R.drawable.overcast)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "drizzle" -> {
            Glide.with(context)
                .load(R.drawable.drizzle)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "light-rain" -> {
            Glide.with(context)
                .load(R.drawable.light_rain)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "rain" -> {
            Glide.with(context)
                .load(R.drawable.rain)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "moderate-rain" -> {
            Glide.with(context)
                .load(R.drawable.moderate_rain)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "heavy-rain" -> {
            Glide.with(context)
                .load(R.drawable.heavy_rain)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "continuous-heavy-rain" -> {
            Glide.with(context)
                .load(R.drawable.continuous_heavy_rain)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "showers" -> {
            Glide.with(context)
                .load(R.drawable.showers)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "wet-snow" -> {
            Glide.with(context)
                .load(R.drawable.wet_snow)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "light-snow" -> {
            Glide.with(context)
                .load(R.drawable.light_snow)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "snow" -> {
            Glide.with(context)
                .load(R.drawable.snow)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "snow-showers" -> {
            Glide.with(context)
                .load(R.drawable.snow_showers)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "hail" -> {
            Glide.with(context)
                .load(R.drawable.hail)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "thunderstorm" -> {
            Glide.with(context)
                .load(R.drawable.thunderstorm)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "thunderstorm-with-rain" -> {
            Glide.with(context)
                .load(R.drawable.thunderstorm_with_rain)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        "thunderstorm-with-hail" -> {
            Glide.with(context)
                .load(R.drawable.thunderstorm_with_hail)
                .thumbnail(0.33f)
                .centerCrop()
                .into(view)
        }
        else -> return
    }
}

