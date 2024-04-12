package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.weatherapp.common.ui.theme.WeatherAppTheme
import com.example.weatherapp.dailyWeather.screen.home.HomeScreen
import com.example.weatherapp.dailyWeather.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val homeViewModel by viewModel<HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val uiState = homeViewModel.dailyWeatherUIState.collectAsState().value

                    HomeScreen(uiState)

                }
            }
        }
    }
}