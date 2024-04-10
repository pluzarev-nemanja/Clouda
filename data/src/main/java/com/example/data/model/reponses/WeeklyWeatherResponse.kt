package com.example.data.model.reponses

data class WeeklyWeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherY>,
    val message: Int
)