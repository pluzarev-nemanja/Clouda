package com.example.domain.repository

import android.util.Log
import com.example.data.model.CurrentWeatherResponse
import com.example.data.model.WeeklyAirPollutionsResponse
import com.example.data.model.WeeklyWeatherResponse
import com.example.data.remote.WeatherApi
import com.example.data.repository.RemoteRepository
import com.example.domain.mapper.ThrowableToErrorModelMapper

class RemoteRepositoryImpl(
    private val api: WeatherApi,
    private val mapper: ThrowableToErrorModelMapper
) : RemoteRepository {

    override suspend fun getCurrentWeatherData(
        latitude: Double,
        longitude: Double,
    ): CurrentWeatherResponse = api.runCatching {
        getCurrentWeathersData(
            latitude = latitude,
            longitude = longitude,
        )
    }.getOrElse {
        throw mapper.mappingObjects(it)
    }

    override suspend fun getWeeklyWeatherData(
        latitude: Double,
        longitude: Double,
    ): WeeklyWeatherResponse = api.runCatching {
        getWeeklyWeatherData(
            latitude = latitude,
            longitude = longitude,
        )
    }.getOrElse {
        throw mapper.mappingObjects(it)
    }

    override suspend fun getWeeklyAirPollutionData(
        latitude: Double,
        longitude: Double,
        startingDay: Long,
        endingDay: Long,
    ): WeeklyAirPollutionsResponse = api.runCatching {
        getWeeklyAirPollutionData(
            latitude = latitude,
            longitude = longitude,
            startingDay = startingDay,
            endingDay = endingDay,
        )
    }.getOrElse {
        throw mapper.mappingObjects(it)
    }

}