package com.example.weatherapp.airPollution.mapper

import com.example.domain.mapper.Mapper
import com.example.domain.model.ErrorResponse
import com.example.weatherapp.airPollution.uiState.AirPollutionUIState

class ErrorResponseToAirPollutionUIStateErrorMapper :
    Mapper<ErrorResponse, AirPollutionUIState.Error> {
    override fun mappingObjects(input: ErrorResponse): AirPollutionUIState.Error =
        when (input) {
            is ErrorResponse.Network -> AirPollutionUIState.Error.Internet(message = "Check your internet connection!")
            is ErrorResponse.Host -> AirPollutionUIState.Error.Server(message = "Server not responding!")
            is ErrorResponse.Unknown -> AirPollutionUIState.Error.Unknown(message = "Unknown error occurred!")
        }
}