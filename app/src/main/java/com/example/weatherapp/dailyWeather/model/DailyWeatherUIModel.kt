package com.example.weatherapp.dailyWeather.model

data class DailyWeatherUIModel(
    val icon: String,
    val currentTime: String,
    val currentTemp: String,
    val location: String,
    val feelsLikeTemp: String,
    val detailDescription: String
)
