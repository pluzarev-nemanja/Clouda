package com.example.weatherapp.dailyWeather.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val mutableDailyWeatherUIState: MutableStateFlow<DailyWeatherUIState> =
        MutableStateFlow(DailyWeatherUIState.Loading)
    val dailyWeatherUIState: StateFlow<DailyWeatherUIState> =
        mutableDailyWeatherUIState.asStateFlow()


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

            mutableDailyWeatherUIState.value = DailyWeatherUIState.Loading

            useCases.runCatching {
                getCurrentWeather(
                    latitude = latitude,
                    longitude = longitude,
                    apiKey = apiKey
                )
            }.mapCatching { dailyWeather ->
                if (dailyWeather != null) mutableDailyWeatherUIState.value =
                    DailyWeatherUIState.Success(data = dailyWeather)
                else mutableDailyWeatherUIState.value = DailyWeatherUIState.Empty
            }.onFailure {

                Timber.tag("ViewModel").d("There is an error")
                mutableDailyWeatherUIState.value =
                    DailyWeatherUIState.Error(message = "Error occurred")
            }.getOrThrow()
        }

    }

    fun observeUIState(dailyWeatherUIState: DailyWeatherUIState) =
        when(dailyWeatherUIState){
            DailyWeatherUIState.Loading ->{

            }
            is DailyWeatherUIState.Success->{

            }
            is DailyWeatherUIState.Error ->{

            }
            DailyWeatherUIState.Empty->{

            }
        }

}