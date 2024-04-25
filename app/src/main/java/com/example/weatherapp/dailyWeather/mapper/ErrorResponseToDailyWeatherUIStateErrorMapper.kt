package com.example.weatherapp.dailyWeather.mapper

import com.example.domain.mapper.Mapper
import com.example.domain.model.ErrorResponse
import com.example.weatherapp.dailyWeather.uiState.DailyWeatherUIState

class ErrorResponseToDailyWeatherUIStateErrorMapper : Mapper<Throwable, DailyWeatherUIState.Error> {

    override fun mappingObjects(input: Throwable): DailyWeatherUIState.Error =
        when (input) {
            is ErrorResponse.Network -> DailyWeatherUIState.Error.Internet(message = "Check your internet connection!")
            is ErrorResponse.Host -> DailyWeatherUIState.Error.Server(message = "Server not responding!")
            else -> DailyWeatherUIState.Error.Unknown(message = "Unknown error occurred!")
        }

}