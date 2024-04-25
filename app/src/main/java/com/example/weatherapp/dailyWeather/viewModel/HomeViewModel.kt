package com.example.weatherapp.dailyWeather.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DailyWeather
import com.example.domain.useCases.UseCases
import com.example.weatherapp.common.location.LocationManager
import com.example.weatherapp.dailyWeather.mapper.DailyWeatherToDailyWeatherUIModelMapper
import com.example.weatherapp.dailyWeather.mapper.ThrowableToDailyWeatherUIStateErrorMapper
import com.example.weatherapp.dailyWeather.model.DailyWeatherUIModel
import com.example.weatherapp.dailyWeather.uiState.DailyWeatherUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val useCases: UseCases,
    private val mapper: DailyWeatherToDailyWeatherUIModelMapper,
    private val errorMapper: ThrowableToDailyWeatherUIStateErrorMapper,
    private val locationManager: LocationManager
) : ViewModel() {

    private val mutableDailyWeatherUIState: MutableStateFlow<DailyWeatherUIState> =
        MutableStateFlow(DailyWeatherUIState.Loading)
    val dailyWeatherUIState: StateFlow<DailyWeatherUIState> =
        mutableDailyWeatherUIState.asStateFlow()



     fun getCurrentWeather() {
        viewModelScope.launch {

            mutableDailyWeatherUIState.value = DailyWeatherUIState.Loading

            useCases.runCatching {
                getCurrentWeather(
                    latitude = locationManager.getLocation()?.latitude ?: 0.0,
                    longitude = locationManager.getLocation()?.longitude ?: 0.0
                )
            }.mapCatching { dailyWeather: DailyWeather ->

                mapper.mappingObjects(dailyWeather)

            }.mapCatching { dailyWeatherUIModel: DailyWeatherUIModel ->

                mutableDailyWeatherUIState.value =
                    DailyWeatherUIState.Success(data = dailyWeatherUIModel)

            }.onFailure{
                mutableDailyWeatherUIState.value = it.convertError()
                Timber.e(it, "ERROR IN HomeViewModel :  ${mutableDailyWeatherUIState.value}")
            }
        }

    }

    private fun Throwable.convertError(): DailyWeatherUIState.Error = errorMapper.run {
        errorMapper.mappingObjects(this@convertError)
    }

}