package com.example.domain.repository

import com.example.data.model.reponses.CurrentWeatherResponse
import com.example.data.model.reponses.WeeklyAirPollutionsResponse
import com.example.data.model.reponses.WeeklyWeatherResponse

interface RemoteRepository {

    suspend fun getCurrentWeathersData(
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