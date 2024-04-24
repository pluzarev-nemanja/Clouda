package com.example.weatherapp.airPollution.uiState

import com.example.weatherapp.airPollution.model.AirPollutionUIModel
import com.example.weatherapp.dailyWeather.uiState.DailyWeatherUIState

sealed class AirPollutionUIState {

    data object Loading : AirPollutionUIState()

    data object Empty : AirPollutionUIState()

    data class Success(val data: List<AirPollutionUIModel>) : AirPollutionUIState()

    sealed class Error: AirPollutionUIState(){
        open val message : String? = null

        data class Unknown(override val message: String?): Error()
        data class Internet(override val message: String?): Error()
        data class Server(override val message: String?): Error()
    }
}