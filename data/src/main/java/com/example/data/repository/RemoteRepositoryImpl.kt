package com.example.data.repository

import com.example.data.model.CurrentWeatherResponse
import com.example.data.model.WeeklyAirPollutionsResponse
import com.example.data.model.WeeklyWeatherResponse
import com.example.data.remote.WeatherApi
import com.example.domain.repository.RemoteRepository

class RemoteRepositoryImpl(
    private val api: WeatherApi
): RemoteRepository {
    override suspend fun getCurrentWeathersData(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): CurrentWeatherResponse {
        return api.getCurrentWeathersData(latitude, longitude, apiKey)
    }

    override suspend fun getWeeklyWeatherData(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): WeeklyWeatherResponse {
        return api.getWeeklyWeatherData(latitude, longitude, apiKey)
    }

    override suspend fun getWeeklyAirPollutionData(
        latitude: Double,
        longitude: Double,
        startingDay: Int,
        endingDay: Int,
        apiKey: String
    ): WeeklyAirPollutionsResponse {
        return api.getWeeklyAirPollutionData(latitude, longitude, startingDay, endingDay, apiKey)
    }

}