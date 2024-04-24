package com.example.weatherapp.airPollution.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ErrorResponse
import com.example.domain.model.PastAirPollution
import com.example.domain.useCases.UseCases
import com.example.weatherapp.airPollution.mapper.ErrorResponseToAirPollutionUIStateErrorMapper
import com.example.weatherapp.airPollution.mapper.PastAirPollutionToAirPollutionUIModelMapper
import com.example.weatherapp.airPollution.model.AirPollutionUIModel
import com.example.weatherapp.airPollution.uiState.AirPollutionUIState
import com.example.weatherapp.common.location.LocationManager
import com.example.weatherapp.weeklyWeather.uiState.WeeklyWeatherUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDateTime
import java.time.ZoneOffset

@RequiresApi(Build.VERSION_CODES.O)
class AirPollutionViewModel(
    private val useCases: UseCases,
    private val mapper: PastAirPollutionToAirPollutionUIModelMapper,
    private val errorMapper: ErrorResponseToAirPollutionUIStateErrorMapper,
    private val locationManager: LocationManager
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


    fun getPastAirPollution() {

        viewModelScope.launch {


            mutableAirPollutionUIState.value = AirPollutionUIState.Loading
            useCases.runCatching {
                getWeeklyAirPollution(
                    latitude = locationManager.getLocation()?.latitude ?: 0.0,
                    longitude = locationManager.getLocation()?.longitude ?: 0.0,
                    startingDay = pastDays,
                    endingDay = today
                )
            }.mapCatching { pastAirPollutionList: List<PastAirPollution> ->

                mapper.mappingObjects(pastAirPollutionList)


            }.mapCatching { airPollutionUIModelList: List<AirPollutionUIModel> ->

                mutableAirPollutionUIState.value =
                    AirPollutionUIState.Success(data = airPollutionUIModelList)

            }.onFailure {
                mutableAirPollutionUIState.value = it.convertError()
                Timber.e(it, "ERROR IN AirPollution :  ${mutableAirPollutionUIState.value}")
            }


        }
    }

    private fun Throwable.convertError(): AirPollutionUIState.Error = runCatching {
        errorMapper
    }.mapCatching {
        errorMapper.mappingObjects(this as ErrorResponse)
    } .getOrThrow()
}