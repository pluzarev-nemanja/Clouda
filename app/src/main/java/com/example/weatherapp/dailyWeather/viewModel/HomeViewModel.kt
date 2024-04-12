package com.example.weatherapp.dailyWeather.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DailyWeather
import com.example.domain.useCases.UseCases
import com.example.weatherapp.common.util.Constants.API_KEY
import com.example.weatherapp.dailyWeather.uiState.DailyWeatherUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val useCases: UseCases
) : ViewModel() {

    private val _dailyWeatherUIState: MutableStateFlow<DailyWeatherUIState> =
        MutableStateFlow(DailyWeatherUIState.Loading)
    val dailyWeatherUIState: StateFlow<DailyWeatherUIState> = _dailyWeatherUIState.asStateFlow()


    init {

        getCurrentWeather(
            latitude = 42.2,
            longitude = 32.2,
            apiKey = API_KEY
        )


    }

    private fun getCurrentWeather(
        latitude: Double = 0.0,
        longitude: Double = 0.0,
        apiKey: String
    ) {
        viewModelScope.launch {
            _dailyWeatherUIState.value = DailyWeatherUIState.Loading
            useCases.runCatching {
                getCurrentWeather(
                    latitude = latitude,
                    longitude = longitude,
                    apiKey = apiKey
                )
            }.mapCatching { dailyWeather ->
                if (dailyWeather != null) _dailyWeatherUIState.value = DailyWeatherUIState.Success(data = dailyWeather)
                else _dailyWeatherUIState.value = DailyWeatherUIState.Empty
            }.onFailure {

                Timber.tag("ViewModel").d("There is an error")
                _dailyWeatherUIState.value = DailyWeatherUIState.Error(message = "Error occurred")
            }.getOrThrow()
        }

    }


}