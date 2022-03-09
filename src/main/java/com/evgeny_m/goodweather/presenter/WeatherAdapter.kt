package com.evgeny_m.goodweather.presenter

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.evgeny_m.goodweather.R
import com.evgeny_m.goodweather.databinding.ItemWeaterBinding
import com.evgeny_m.goodweather.domain.model.Weather
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class WeatherAdapter(private val activity: Activity) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private var listTemp = mutableListOf<Weather>()

    class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemWeaterBinding.bind(view)
        val temp = binding.temp
        val icon = binding.imageIcon
        val image = binding.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weater, parent, false)
        return WeatherViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = listTemp[position]
        val temp = weather.temp.toInt()
        if (temp > 0) {
            holder.temp.text = "+$temp"
        } else {
            holder.temp.text = "$temp"
        }
        loadImage(holder.image, activity, weather.condition)

        val url: Uri = "https://yastatic.net/weather/i/icons/funky/dark/${weather.icon}.svg".toUri()
        GlideToVectorYou.justLoadImage(activity, url, holder.icon)
    }

    override fun getItemCount(): Int {
        return listTemp.size
    }

    fun addItems(list: List<Weather>, string: String) {
        list.forEach {
            if (it.date != string && !listTemp.contains(it)) {
                listTemp.add(it)
            }
        }
        notifyDataSetChanged()
    }
}