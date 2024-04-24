package com.example.weatherapp.weeklyWeather.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ErrorResponse
import com.example.domain.model.WeeklyWeather
import com.example.domain.useCases.UseCases
import com.example.weatherapp.common.location.LocationManager
import com.example.weatherapp.weeklyWeather.mapper.ErrorResponseToWeeklyWeatherUIStateErrorMapper
import com.example.weatherapp.weeklyWeather.mapper.WeeklyWeatherToWeeklyWeatherUIModelMapper
import com.example.weatherapp.weeklyWeather.model.WeeklyWeatherUIModel
import com.example.weatherapp.weeklyWeather.uiState.WeeklyWeatherUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class WeeklyWeatherViewModel(
    private val useCases: UseCases,
    private val mapper: WeeklyWeatherToWeeklyWeatherUIModelMapper,
    private val errorMapper: ErrorResponseToWeeklyWeatherUIStateErrorMapper,
    private val locationManager: LocationManager
) : ViewModel() {


    private val mutableWeeklyWeatherUIState: MutableStateFlow<WeeklyWeatherUIState> =
        MutableStateFlow(WeeklyWeatherUIState.Loading)

    val weeklyWeatherUIState: StateFlow<WeeklyWeatherUIState> =
        mutableWeeklyWeatherUIState.asStateFlow()


    fun getWeeklyWeather() {
        viewModelScope.launch {

            mutableWeeklyWeatherUIState.value = WeeklyWeatherUIState.Loading

            useCases.runCatching {
                getWeeklyWeather(
                    latitude = locationManager.getLocation()?.latitude ?: 0.0,
                    longitude = locationManager.getLocation()?.longitude ?: 0.0
                )
            }.mapCatching { weeklyWeatherList: List<WeeklyWeather> ->
                mapper.mappingObjects(weeklyWeatherList)
            }.mapCatching { weeklyWeatherUIModelList: List<WeeklyWeatherUIModel> ->

                mutableWeeklyWeatherUIState.value =
                    WeeklyWeatherUIState.Success(data = weeklyWeatherUIModelList)

            }.onFailure {
                mutableWeeklyWeatherUIState.value = errorMapper.mappingObjects(it as ErrorResponse)
                Timber.e(it, "ERROR IN WeeklyWeather :  ${mutableWeeklyWeatherUIState.value}")

            }

        }
    }
}

