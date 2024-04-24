package com.example.weatherapp.weeklyWeather.mapper

import com.example.domain.mapper.Mapper
import com.example.domain.model.ErrorResponse
import com.example.weatherapp.weeklyWeather.uiState.WeeklyWeatherUIState

class ErrorResponseToWeeklyWeatherUIStateErrorMapper : Mapper<ErrorResponse, WeeklyWeatherUIState.Error> {

    override fun mappingObjects(input: ErrorResponse): WeeklyWeatherUIState.Error =
        when (input) {
            is ErrorResponse.Network -> WeeklyWeatherUIState.Error.Internet(message = "Check your internet connection!")
            is ErrorResponse.Host -> WeeklyWeatherUIState.Error.Server(message = "Server not responding!")
            is ErrorResponse.Unknown -> WeeklyWeatherUIState.Error.Unknown(message = "Unknown error occurred!")
        }
}