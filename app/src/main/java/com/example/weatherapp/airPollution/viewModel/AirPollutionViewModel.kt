package com.example.weatherapp.airPollution.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.PastAirPollution
import com.example.domain.useCases.UseCases
import com.example.weatherapp.airPollution.uiState.AirPollutionUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDateTime
import java.time.ZoneOffset

@RequiresApi(Build.VERSION_CODES.O)
class AirPollutionViewModel(
    private val useCases: UseCases
) : ViewModel() {

    private val mutableAirPollutionUIState: MutableStateFlow<AirPollutionUIState> =
        MutableStateFlow(AirPollutionUIState.Loading)
    val airPollutionUIState: StateFlow<AirPollutionUIState> =
        mutableAirPollutionUIState.asStateFlow()

    private val today = LocalDateTime
        .now()
        .toEpochSecond(ZoneOffset.UTC)

    private val pastDays = LocalDateTime.now()
        .minusDays(5)
        .withHour(12)
        .toEpochSecond(ZoneOffset.ofHours(3))

    init {

        getPastAirPollution(
            latitude = 42.2,
            longitude = 33.2,
        )

    }


    private fun getPastAirPollution(
        latitude: Double = 0.0,
        longitude: Double = 0.0,
    ) {

        viewModelScope.launch {


            mutableAirPollutionUIState.value = AirPollutionUIState.Loading
            useCases.runCatching {
                getWeeklyAirPollution(
                    latitude = latitude,
                    longitude = longitude,
                    startingDay = pastDays,
                    endingDay = today
                )
            }.mapCatching { pastAirPollutionList: List<PastAirPollution> ->
                if (pastAirPollutionList.isNotEmpty()) mutableAirPollutionUIState.value =
                    AirPollutionUIState.Success(data = pastAirPollutionList)
                else mutableAirPollutionUIState.value = AirPollutionUIState.Empty


            }.onFailure {
                Timber.tag("AirPollutionViewModel").d("Something went wrong!")
                mutableAirPollutionUIState.value = AirPollutionUIState.Error("Error occurred!")
            }.getOrThrow()


        }
    }


}