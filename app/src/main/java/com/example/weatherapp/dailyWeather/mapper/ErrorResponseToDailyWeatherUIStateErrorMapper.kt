package com.example.weatherapp.dailyWeather.mapper

import com.example.domain.mapper.Mapper
import com.example.domain.model.ErrorResponse
import com.example.weatherapp.dailyWeather.uiState.DailyWeatherUIState

class ErrorResponseToDailyWeatherUIStateErrorMapper : Mapper<ErrorResponse, DailyWeatherUIState.Error> {

    override fun mappingObjects(input: ErrorResponse): DailyWeatherUIState.Error =
        when (input) {
            is ErrorResponse.Network -> DailyWeatherUIState.Error.Internet(message = "Check your internet connection!")
            is ErrorResponse.Host -> DailyWeatherUIState.Error.Server(message = "Server not responding!")
            is ErrorResponse.Unknown -> DailyWeatherUIState.Error.Unknown(message = "Unknown error occurred!")
        }

}