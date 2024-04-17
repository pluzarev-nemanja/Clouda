package com.example.domain.model

data class WeeklyWeather(
    val city: String,
    val countryCode : String,
    val time : Long,
    val minTemp : Double,
    val maxTemp : Double,
    val sunset : Long,
    val sunrise: Long,
    val windSpeed : Double,
    val icon : String

)
