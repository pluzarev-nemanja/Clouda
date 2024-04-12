package com.example.weatherapp.dailyWeather.uiState

import com.example.domain.model.DailyWeather

sealed class DailyWeatherUIState {
    data object Loading: DailyWeatherUIState()
    data class Success(val data : DailyWeather) : DailyWeatherUIState()
    data class Error(val message : String?) : DailyWeatherUIState()

    data object Empty : DailyWeatherUIState()
}