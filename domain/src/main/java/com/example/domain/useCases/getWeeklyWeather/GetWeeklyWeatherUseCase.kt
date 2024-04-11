package com.example.domain.useCases.getWeeklyWeather

import android.util.Log
import com.example.data.repository.RemoteRepository
import com.example.domain.mapper.WeeklyWeatherResponseToWeeklyWeatherMapper
import com.example.domain.model.WeeklyWeather

class GetWeeklyWeatherUseCase(
    private val remoteRepository: RemoteRepository,
    private val mapper: WeeklyWeatherResponseToWeeklyWeatherMapper

) {
    suspend operator fun invoke(
        latitude: Double = 0.0,
        longitude: Double = 0.0,
        apiKey: String
    ): List<WeeklyWeather> = remoteRepository.runCatching {
        getWeeklyWeatherData(
            latitude = latitude,
            longitude = longitude,
            apiKey = apiKey
        )
    }.mapCatching {
        mapper.mappingObjects(it)
    }.onFailure {
        Log.d(this::class.simpleName, "There is an error")
    }.getOrThrow()

}