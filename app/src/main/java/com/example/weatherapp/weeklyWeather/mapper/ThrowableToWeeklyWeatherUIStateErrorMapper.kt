package com.example.weatherapp.weeklyWeather.mapper

import com.example.domain.mapper.Mapper
import com.example.domain.model.ErrorResponse
import com.example.weatherapp.weeklyWeather.uiState.WeeklyWeatherUIState

class ThrowableToWeeklyWeatherUIStateErrorMapper : Mapper<Throwable, WeeklyWeatherUIState.Error> {

    override fun mappingObjects(input: Throwable): WeeklyWeatherUIState.Error =
        when (input) {
            is ErrorResponse.Network -> WeeklyWeatherUIState.Error.Internet(message = "Check your internet connection!")
            is ErrorResponse.Host -> WeeklyWeatherUIState.Error.Server(message = "Server not responding!")
            else -> WeeklyWeatherUIState.Error.Unknown(message = "Unknown error occurred!")
        }
}