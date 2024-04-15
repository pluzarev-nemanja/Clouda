package com.example.weatherapp.airPollution.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.PastAirPollution
import com.example.domain.useCases.UseCases
import com.example.weatherapp.airPollution.uiState.AirPollutionUIState
import com.example.weatherapp.common.util.Constants.API_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class AirPollutionViewModel(
    private val useCases: UseCases
) : ViewModel() {

    private val mutableAirPollutionUIState: MutableStateFlow<AirPollutionUIState> =
        MutableStateFlow(AirPollutionUIState.Loading)
    val airPollutionUIState: StateFlow<AirPollutionUIState> =
        mutableAirPollutionUIState.asStateFlow()

    init {
        getPastAirPollution(
            latitude = 42.2,
            longitude = 33.2,
            startingDay = 1606223802,
            endingDay = 1606482999,
            apiKey = API_KEY
        )
    }


    private fun getPastAirPollution(
        latitude: Double = 0.0,
        longitude: Double = 0.0,
        startingDay: Int = 0,
        endingDay: Int = 0,
        apiKey: String
    ) {

        viewModelScope.launch {

            mutableAirPollutionUIState.value = AirPollutionUIState.Loading
            useCases.runCatching {
                getWeeklyAirPollution(
                    latitude = latitude,
                    longitude = longitude,
                    startingDay = startingDay,
                    endingDay = endingDay,
                    apiKey = apiKey
                )
            }.mapCatching { pastAirPollutionList: List<PastAirPollution> ->
                if (pastAirPollutionList != null) mutableAirPollutionUIState.value =
                    AirPollutionUIState.Success(data = pastAirPollutionList)
                else mutableAirPollutionUIState.value = AirPollutionUIState.Empty
            }.onFailure {
                Timber.tag("AirPollutionViewModel").d("Something went wrong!")
                mutableAirPollutionUIState.value = AirPollutionUIState.Error("Error occurred!")
            }.getOrThrow()


        }
    }

}