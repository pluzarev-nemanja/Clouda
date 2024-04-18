package com.example.weatherapp.airPollution.model

data class AirPollutionUIModel(
    val date : String,
    val carbonMonoxide : String,
    val ammonia: String,
    val nitrogenDioxide : String,
    val nitrogenMonoxide : String,
    val ozone : String,
    val pm10: String,
    val pm25 : String,
    val sulphur: String
)
