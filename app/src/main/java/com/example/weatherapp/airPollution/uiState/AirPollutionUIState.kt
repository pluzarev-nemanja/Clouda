package com.example.weatherapp.airPollution.uiState

import com.example.weatherapp.airPollution.model.AirPollutionUIModel

sealed class AirPollutionUIState {

    data object Loading : AirPollutionUIState()

    data object Empty : AirPollutionUIState()

    data class Success(val data: List<AirPollutionUIModel>) : AirPollutionUIState()

    data class Error(val message: String?) : AirPollutionUIState()
}