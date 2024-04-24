package com.example.weatherapp.airPollution.mapper

import com.example.domain.mapper.Mapper
import com.example.domain.model.ErrorResponse
import com.example.weatherapp.airPollution.uiState.AirPollutionUIState.Error

class ErrorResponseToAirPollutionUIStateErrorMapper :
    Mapper<ErrorResponse, Error> {
    override fun mappingObjects(input: ErrorResponse): Error =
        when (input) {
            is ErrorResponse.Network -> Error.Internet(message = "Check your internet connection!")
            is ErrorResponse.Host -> Error.Server(message = "Server not responding!")
            is ErrorResponse.Unknown -> Error.Unknown(message = "Unknown error occurred!")
        }
}