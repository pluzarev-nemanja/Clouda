package com.example.domain.repository

import com.example.data.model.CurrentWeatherResponse
import com.example.data.model.WeeklyAirPollutionsResponse
import com.example.data.model.WeeklyWeatherResponse
import com.example.data.remote.WeatherApi
import com.example.data.repository.RemoteRepository

class RemoteRepositoryImpl(
    private val api: WeatherApi
) : RemoteRepository {
    override suspend fun getCurrentWeatherData(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): CurrentWeatherResponse = api.getCurrentWeathersData(
        latitude = latitude,
        longitude = longitude,
        apiKey = apiKey
    )

    override suspend fun getWeeklyWeatherData(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): WeeklyWeatherResponse = api.getWeeklyWeatherData(
        latitude = latitude,
        longitude = longitude,
        apiKey = apiKey
    )

    override suspend fun getWeeklyAirPollutionData(
        latitude: Double,
        longitude: Double,
        startingDay: Int,
        endingDay: Int,
        apiKey: String
    ): WeeklyAirPollutionsResponse = api.getWeeklyAirPollutionData(
        latitude = latitude,
        longitude = longitude,
        startingDay = startingDay,
        endingDay = endingDay,
        apiKey = apiKey
    )


}