package com.example.weatherapp.weeklyWeather.model

data class WeeklyWeatherUIModel(
    val city: String,
    val countryCode: String,
    val icon: String,
    val day : String,
    val maxTemp: String,
    val minTemp: String,
    val sunset: String,
    val sunrise: String,
    val windSpeed: String
)
