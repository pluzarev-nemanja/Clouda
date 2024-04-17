package com.example.weatherapp.weeklyWeather.uiState

import com.example.domain.model.WeeklyWeather

sealed class WeeklyWeatherUIState {


    data object Loading: WeeklyWeatherUIState()

    data object Empty: WeeklyWeatherUIState()

    data class Success(val data : List<WeeklyWeather>): WeeklyWeatherUIState()

    data class Error(val message: String?) : WeeklyWeatherUIState()
}