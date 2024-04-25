package com.example.weatherapp.airPollution.mapper

import com.example.domain.mapper.Mapper
import com.example.domain.model.ErrorResponse
import com.example.weatherapp.airPollution.uiState.AirPollutionUIState

class ThrowableToAirPollutionUIStateErrorMapper :
    Mapper<Throwable, AirPollutionUIState.Error> {
    override fun mappingObjects(input: Throwable): AirPollutionUIState.Error =
        when (input) {
            is ErrorResponse.Network -> AirPollutionUIState.Error.Internet(message = "Check your internet connection!")
            is ErrorResponse.Host -> AirPollutionUIState.Error.Server(message = "Server not responding!")
            else -> AirPollutionUIState.Error.Unknown(message = "Unknown error occurred!")
        }
}