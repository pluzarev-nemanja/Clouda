package com.example.weatherapp.weeklyWeather.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.WeeklyWeather
import com.example.domain.useCases.UseCases
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
    private val mapper: WeeklyWeatherToWeeklyWeatherUIModelMapper
): ViewModel() {


    private val mutableWeeklyWeatherUIState : MutableStateFlow<WeeklyWeatherUIState> =
        MutableStateFlow(WeeklyWeatherUIState.Loading)

    val weeklyWeatherUIState: StateFlow<WeeklyWeatherUIState> =
        mutableWeeklyWeatherUIState.asStateFlow()


    init {

       getWeeklyWeather(
           latitude = 44.3,
           longitude = 22.3
       )

    }

    private fun getWeeklyWeather(
        latitude: Double = 0.0,
        longitude: Double = 0.0
    ){
        viewModelScope.launch {

            mutableWeeklyWeatherUIState.value = WeeklyWeatherUIState.Loading

            useCases.runCatching {
                getWeeklyWeather(
                    latitude = latitude,
                    longitude = longitude
                )
            }.mapCatching { weeklyWeatherList: List<WeeklyWeather> ->
                mapper.mappingObjects(weeklyWeatherList)
            }.mapCatching { weeklyWeatherUIModelList : List<WeeklyWeatherUIModel> ->

                mutableWeeklyWeatherUIState.value = WeeklyWeatherUIState.Success(data = weeklyWeatherUIModelList)

            }.onFailure {
                Timber.e(it, "Something went wrong!")
                mutableWeeklyWeatherUIState.value = WeeklyWeatherUIState.Error("Error occurred")
            }.getOrThrow()

        }
    }
}

