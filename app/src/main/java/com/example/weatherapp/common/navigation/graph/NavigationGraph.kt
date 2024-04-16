package com.example.weatherapp.common.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.about.screen.about.AboutScreen
import com.example.weatherapp.airPollution.screen.pollution.AirPollutionScreen
import com.example.weatherapp.airPollution.uiState.AirPollutionUIState
import com.example.weatherapp.common.navigation.routes.BottomNavItem
import com.example.weatherapp.dailyWeather.screen.home.HomeScreen
import com.example.weatherapp.dailyWeather.uiState.DailyWeatherUIState
import com.example.weatherapp.weeklyWeather.screen.weekly.WeeklyWeatherScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    dailyWeatherUIState: DailyWeatherUIState,
    paddingValues: PaddingValues,
    airPollutionUIState: AirPollutionUIState,
    onNavigate: () -> Unit,
    formatDate : (Long) -> String
) {


    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {

        composable(BottomNavItem.Home.route) {
            HomeScreen(
                dailyWeatherUIState = dailyWeatherUIState,
                paddingValues = paddingValues,
                navController = navController,
                onNavigate = onNavigate
            )
        }

        composable(BottomNavItem.WeeklyWeather.route) {
            WeeklyWeatherScreen(
                paddingValues = paddingValues
            )
        }
        composable(BottomNavItem.AirPollution.route) {
            AirPollutionScreen(
                paddingValues = paddingValues,
                airPollutionUIState = airPollutionUIState,
                formatDate = formatDate
            )
        }

        composable(BottomNavItem.About.route) {
            AboutScreen(
                paddingValues = paddingValues
            )
        }

    }
}