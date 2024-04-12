package com.example.weatherapp.common.navigation.routes

import androidx.annotation.DrawableRes
import com.example.weatherapp.R

sealed class BottomNavItem(val route: String,@DrawableRes val icon: Int,val title : String) {

    data object Home : BottomNavItem(route = "homeScreen", icon = R.drawable.ic_home, title = "Home")
    data object AirPollution : BottomNavItem(route = "airPollutionScreen", icon = R.drawable.ic_pollution, title = "Air Pollution")

    data object WeeklyWeather : BottomNavItem(route = "weeklyWeatherScreen", icon = R.drawable.ic_weekly, title = "Weekly Weather")

    data object About : BottomNavItem(route = "aboutScreen", icon = R.drawable.ic_about, title = "About")

}