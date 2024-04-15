package com.example.weatherapp.airPollution.uiState

import com.example.domain.model.PastAirPollution

sealed class AirPollutionUIState {

    data object Loading : AirPollutionUIState()

    data object Empty : AirPollutionUIState()

    data class Success(val data : List<PastAirPollution>): AirPollutionUIState()

    data class Error(val message : String?) : AirPollutionUIState()
}