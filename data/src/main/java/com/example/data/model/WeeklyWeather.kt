package com.example.data.model

data class WeeklyWeather(
    val minTemp : Double,
    val maxTemp : Double,
    val sunset : Int,
    val sunrise: Int,
    val windSpeed : Double,
    val icon : String

)
