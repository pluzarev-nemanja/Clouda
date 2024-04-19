package com.example.weatherapp.weeklyWeather.uiState

import com.example.weatherapp.weeklyWeather.model.WeeklyWeatherUIModel

sealed class WeeklyWeatherUIState {


    data object Loading : WeeklyWeatherUIState()

    data object Empty : WeeklyWeatherUIState()

    data class Success(val data: List<WeeklyWeatherUIModel>) : WeeklyWeatherUIState()

    data class Error(val message: String?) : WeeklyWeatherUIState()
}