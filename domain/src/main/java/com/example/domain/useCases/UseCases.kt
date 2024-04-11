package com.example.domain.useCases

import com.example.domain.useCases.getCurrentWeather.GetCurrentWeatherUseCase
import com.example.domain.useCases.getWeeklyAirPollution.GetWeeklyAirPollutionUseCase
import com.example.domain.useCases.getWeeklyWeather.GetWeeklyWeatherUseCase

data class UseCases(
    val getCurrentWeather: GetCurrentWeatherUseCase,
    val getWeeklyAirPollution: GetWeeklyAirPollutionUseCase,
    val getWeeklyWeather: GetWeeklyWeatherUseCase
)
