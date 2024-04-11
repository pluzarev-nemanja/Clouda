package com.example.data.model

import com.google.gson.annotations.SerializedName

data class WeatherY(
    val clouds: CloudsX,
    @SerializedName("dt")
    val time: Int,
    @SerializedName("dt_txt")
    val timeTxt: String,
    val main: MainXX,
    val pop: Double,
    val rain: RainX,
    val sys: SysX,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind: WindX
)