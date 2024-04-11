package com.example.data.model

import com.google.gson.annotations.SerializedName

data class MainXX(
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("grnd_level")
    val groundLevel: Int,
    val humidity: Int,
    val pressure: Int,
    @SerializedName("sea_level")
    val seaLevel: Int,
    val temp: Double,
    @SerializedName("temp_kf")
    val tempKf: Double,
    @SerializedName("temp_max")
    val maxTemp: Double,
    @SerializedName("temp_min")
    val minTemp: Double
)