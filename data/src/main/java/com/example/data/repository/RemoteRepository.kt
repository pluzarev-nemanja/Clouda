package com.example.data.repository

import com.example.data.model.CurrentWeatherResponse
import com.example.data.model.WeeklyAirPollutionsResponse
import com.example.data.model.WeeklyWeatherResponse

interface RemoteRepository {

    suspend fun getCurrentWeatherData(
        latitude: Double = 0.0,
        longitude: Double = 0.0,
    ): CurrentWeatherResponse


    suspend fun getWeeklyWeatherData(
        latitude: Double = 0.0,
        longitude: Double = 0.0,
    ): WeeklyWeatherResponse


    suspend fun getWeeklyAirPollutionData(
        latitude: Double = 0.0,
        longitude: Double = 0.0,
        startingDay: Long = 0L,
        endingDay: Long = 0L,
    ): WeeklyAirPollutionsResponse

}