package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.airPollution.viewModel.AirPollutionViewModel
import com.example.weatherapp.common.ui.theme.WeatherAppTheme
import com.example.weatherapp.dailyWeather.viewModel.HomeViewModel
import com.example.weatherapp.weeklyWeather.viewModel.WeeklyWeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val homeViewModel by viewModel<HomeViewModel>()
    private val airPollutionViewModel by viewModel<AirPollutionViewModel>()
    private val weeklyWeatherViewModel by viewModel<WeeklyWeatherViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val dailyWeatherUIState =
                        homeViewModel.dailyWeatherUIState.collectAsStateWithLifecycle().value
                    val airPollutionUIState =
                        airPollutionViewModel.airPollutionUIState.collectAsStateWithLifecycle().value
                    val weeklyWeatherUIState =
                        weeklyWeatherViewModel.weeklyWeatherUIState.collectAsStateWithLifecycle().value


                    MainScreen(
                        navController = rememberNavController(),
                        dailyWeatherUIState = dailyWeatherUIState,
                        airPollutionUIState = airPollutionUIState,
                        weeklyWeatherUIState = weeklyWeatherUIState
                    )

                }
            }
        }
    }
}