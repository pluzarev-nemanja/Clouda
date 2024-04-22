package com.example.weatherapp.airPollution.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.PastAirPollution
import com.example.domain.useCases.UseCases
import com.example.weatherapp.airPollution.mapper.PastAirPollutionToAirPollutionUIModelMapper
import com.example.weatherapp.airPollution.model.AirPollutionUIModel
import com.example.weatherapp.airPollution.uiState.AirPollutionUIState
import com.example.weatherapp.common.model.LatLong
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDateTime
import java.time.ZoneOffset

@RequiresApi(Build.VERSION_CODES.O)
class AirPollutionViewModel(
    private val latLong: LatLong,
    private val useCases: UseCases,
    private val mapper: PastAirPollutionToAirPollutionUIModelMapper
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

    init { getPastAirPollution() }


    private fun getPastAirPollution() {

        viewModelScope.launch {


            mutableAirPollutionUIState.value = AirPollutionUIState.Loading
            useCases.runCatching {
                getWeeklyAirPollution(
                    latitude = latLong.latitude,
                    longitude = latLong.longitude,
                    startingDay = pastDays,
                    endingDay = today
                )
            }.mapCatching { pastAirPollutionList: List<PastAirPollution> ->

                mapper.mappingObjects(pastAirPollutionList)


            }.mapCatching { airPollutionUIModelList: List<AirPollutionUIModel> ->

                mutableAirPollutionUIState.value =
                    AirPollutionUIState.Success(data = airPollutionUIModelList)

            }.onFailure {
                Timber.e(it, "Something went wrong!")
                mutableAirPollutionUIState.value = AirPollutionUIState.Error("Error occurred!")
            }.getOrThrow()


        }
    }


}