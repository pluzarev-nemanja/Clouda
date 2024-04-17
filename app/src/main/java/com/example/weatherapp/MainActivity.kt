package com.example.weatherapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.airPollution.viewModel.AirPollutionViewModel
import com.example.weatherapp.common.navigation.graph.NavigationGraph
import com.example.weatherapp.common.ui.theme.WeatherAppTheme
import com.example.weatherapp.common.util.DateFormatter.formatDate
import com.example.weatherapp.dailyWeather.screen.home.HomeScreen
import com.example.weatherapp.dailyWeather.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : ComponentActivity() {

    private val homeViewModel by viewModel<HomeViewModel>()
    private val airPollutionViewModel by viewModel<AirPollutionViewModel>()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val dailyWeatherUIState = homeViewModel.dailyWeatherUIState.collectAsStateWithLifecycle().value
                    val airPollutionUIState = airPollutionViewModel.airPollutionUIState.collectAsStateWithLifecycle().value

                    MainScreen(
                        navController = rememberNavController(),
                        dailyWeatherUIState = dailyWeatherUIState,
                        airPollutionUIState = airPollutionUIState,
                        formatDate = { date ->
                            date.formatDate()
                        }
                    )

                }
            }
        }
    }
}