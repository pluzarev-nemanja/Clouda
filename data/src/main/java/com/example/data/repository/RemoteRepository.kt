package com.example.data.repository

import com.example.data.model.CurrentWeatherResponse
import com.example.data.model.WeeklyAirPollutionsResponse
import com.example.data.model.WeeklyWeatherResponse

interface RemoteRepository {

    suspend fun getCurrentWeatherData(
        latitude: Double = 0.0,
        longitude: Double = 0.0,
        apiKey: String
    ): CurrentWeatherResponse


    suspend fun getWeeklyWeatherData(
        latitude: Double = 0.0,
        longitude: Double = 0.0,
        apiKey: String
    ): WeeklyWeatherResponse


    suspend fun getWeeklyAirPollutionData(
        latitude: Double = 0.0,
        longitude: Double = 0.0,
        startingDay: Int = 0,
        endingDay: Int = 0,
        apiKey: String
    ): WeeklyAirPollutionsResponse

}