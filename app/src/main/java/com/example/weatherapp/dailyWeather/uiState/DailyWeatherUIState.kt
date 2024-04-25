package com.example.weatherapp.dailyWeather.uiState

import com.example.weatherapp.dailyWeather.model.DailyWeatherUIModel

sealed class DailyWeatherUIState {
    data object Loading : DailyWeatherUIState()
    data class Success(val data: DailyWeatherUIModel) : DailyWeatherUIState()

    sealed class Error: DailyWeatherUIState(){
        open val message : String? = null

        data class Unknown(override val message: String?): Error()
        data class Internet(override val message: String?): Error()
        data class Server(override val message: String?): Error()
    }

    data object Empty : DailyWeatherUIState()
}