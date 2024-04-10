package com.example.data.model

data class WeatherY(
    val clouds: CloudsX,
    val dt: Int,
    val dt_txt: String,
    val main: MainXX,
    val pop: Double,
    val rain: RainX,
    val sys: SysX,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind: WindX
)