package com.example.weatherapp.dailyWeather.mapper

import com.example.domain.mapper.Mapper
import com.example.domain.model.ErrorResponse
import com.example.weatherapp.dailyWeather.uiState.DailyWeatherUIState.Error

class ErrorResponseToDailyWeatherUIStateErrorMapper : Mapper<ErrorResponse, Error> {

    override fun mappingObjects(input: ErrorResponse): Error =
        when (input) {
            is ErrorResponse.Network -> Error.Internet(message = "Check your internet connection!")
            is ErrorResponse.Host -> Error.Server(message = "Server not responding!")
            is ErrorResponse.Unknown -> Error.Unknown(message = "Unknown error occurred!")
        }

}