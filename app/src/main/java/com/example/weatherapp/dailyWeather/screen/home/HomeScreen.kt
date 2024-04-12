package com.example.weatherapp.dailyWeather.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weatherapp.dailyWeather.uiState.DailyWeatherUIState

@Composable
fun HomeScreen(
    dailyWeatherUIState: DailyWeatherUIState,
    paddingValues: PaddingValues
) {

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Text(text = "Home Screen")
    }

}