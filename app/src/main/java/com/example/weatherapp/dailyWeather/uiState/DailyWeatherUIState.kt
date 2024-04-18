package com.example.weatherapp.dailyWeather.uiState

import com.example.weatherapp.dailyWeather.model.DailyWeatherUIModel

sealed class DailyWeatherUIState {
    data object Loading : DailyWeatherUIState()
    data class Success(val data: DailyWeatherUIModel) : DailyWeatherUIState()
    data class Error(val message: String?) : DailyWeatherUIState()

    data object Empty : DailyWeatherUIState()
}