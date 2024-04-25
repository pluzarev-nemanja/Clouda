package com.example.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.airPollution.viewModel.AirPollutionViewModel
import com.example.weatherapp.common.components.SinglePermissionDialog
import com.example.weatherapp.common.ui.theme.WeatherAppTheme
import com.example.weatherapp.common.util.Constants.DARK_MODE_PREF
import com.example.weatherapp.dailyWeather.viewModel.HomeViewModel
import com.example.weatherapp.weeklyWeather.viewModel.WeeklyWeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {


    private val homeViewModel: HomeViewModel by viewModel()
    private val airPollutionViewModel: AirPollutionViewModel by viewModel()
    private val weeklyWeatherViewModel: WeeklyWeatherViewModel by viewModel()
    private val mainViewModel: MainViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {

            val isInDarkTheme = mainViewModel.isInDarkTheme.collectAsStateWithLifecycle().value

            WeatherAppTheme(darkTheme = isInDarkTheme) {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    SinglePermissionDialog(permission = Manifest.permission.ACCESS_COARSE_LOCATION) {


                        LaunchedEffect(key1 = true) {
                            homeViewModel.getCurrentWeather()
                            airPollutionViewModel.getPastAirPollution()
                            weeklyWeatherViewModel.getWeeklyWeather()
                        }


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
                            weeklyWeatherUIState = weeklyWeatherUIState,
                            isInDarkTheme = isInDarkTheme,
                            onDarkThemeSwitch = {
                                mainViewModel.saveTheme(DARK_MODE_PREF, isInDarkTheme.not())
                            },
                            onRetryClick = {
                                homeViewModel.getCurrentWeather()
                                airPollutionViewModel.getPastAirPollution()
                                weeklyWeatherViewModel.getWeeklyWeather()
                            }
                        )


                    }


                }

            }
        }
    }
}

