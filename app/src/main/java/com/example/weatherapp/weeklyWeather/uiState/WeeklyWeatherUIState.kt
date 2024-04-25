package com.example.weatherapp.weeklyWeather.uiState

import com.example.weatherapp.weeklyWeather.model.WeeklyWeatherUIModel

sealed class WeeklyWeatherUIState {


    data object Loading : WeeklyWeatherUIState()

    data object Empty : WeeklyWeatherUIState()

    data class Success(val data: List<WeeklyWeatherUIModel>) : WeeklyWeatherUIState()

    sealed class Error : WeeklyWeatherUIState() {
        open val message: String? = null

        data class Unknown(override val message: String?) : Error()
        data class Internet(override val message: String?) : Error()
        data class Server(override val message: String?) : Error()
    }
}