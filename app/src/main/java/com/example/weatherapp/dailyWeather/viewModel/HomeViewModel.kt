package com.example.weatherapp.dailyWeather.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DailyWeather
import com.example.domain.useCases.UseCases
import com.example.weatherapp.dailyWeather.mapper.DailyWeatherToDailyWeatherUIModelMapper
import com.example.weatherapp.dailyWeather.model.DailyWeatherUIModel
import com.example.weatherapp.dailyWeather.uiState.DailyWeatherUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val useCases: UseCases,
    private val mapper: DailyWeatherToDailyWeatherUIModelMapper
) : ViewModel() {

    private val mutableDailyWeatherUIState: MutableStateFlow<DailyWeatherUIState> =
        MutableStateFlow(DailyWeatherUIState.Loading)
    val dailyWeatherUIState: StateFlow<DailyWeatherUIState> =
        mutableDailyWeatherUIState.asStateFlow()


    init {

        getCurrentWeather(
            latitude = 42.2,
            longitude = 32.2,
        )


    }

    private fun getCurrentWeather(
        latitude: Double = 0.0,
        longitude: Double = 0.0,
    ) {
        viewModelScope.launch {

            mutableDailyWeatherUIState.value = DailyWeatherUIState.Loading

            useCases.runCatching {
                getCurrentWeather(
                    latitude = latitude,
                    longitude = longitude,
                )
            }.mapCatching { dailyWeather: DailyWeather ->

                mapper.mappingObjects(dailyWeather)

            }.mapCatching { dailyWeatherUIModel: DailyWeatherUIModel ->

                mutableDailyWeatherUIState.value = DailyWeatherUIState.Success(data = dailyWeatherUIModel)

            }.onFailure {

                Timber.e(it, "Something went wrong!")
                mutableDailyWeatherUIState.value =
                    DailyWeatherUIState.Error(message = "Error occurred")
            }.getOrThrow()
        }

    }

}