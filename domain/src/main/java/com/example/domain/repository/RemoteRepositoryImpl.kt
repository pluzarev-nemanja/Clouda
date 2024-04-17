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
    ): CurrentWeatherResponse = api.getCurrentWeathersData(
        latitude = latitude,
        longitude = longitude,
    )

    override suspend fun getWeeklyWeatherData(
        latitude: Double,
        longitude: Double,
    ): WeeklyWeatherResponse = api.getWeeklyWeatherData(
        latitude = latitude,
        longitude = longitude,
    )

    override suspend fun getWeeklyAirPollutionData(
        latitude: Double,
        longitude: Double,
        startingDay: Long,
        endingDay: Long,
    ): WeeklyAirPollutionsResponse = api.getWeeklyAirPollutionData(
        latitude = latitude,
        longitude = longitude,
        startingDay = startingDay,
        endingDay = endingDay,
    )


}