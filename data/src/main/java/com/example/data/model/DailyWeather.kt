package com.example.data.model

data class DailyWeather(
    val location: String,
    val dateAndTime: Int,
    val currentTemp: Double,
    val feelsLike : Double,
    val icon: String,
    val weatherDescription : String
)