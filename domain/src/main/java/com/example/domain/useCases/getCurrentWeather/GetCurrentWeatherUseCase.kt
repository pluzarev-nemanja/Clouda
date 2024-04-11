package com.example.domain.useCases.getCurrentWeather

import android.util.Log
import com.example.data.repository.RemoteRepository
import com.example.domain.mapper.CurrentWeatherResponseToDailyWeatherMapper
import com.example.domain.model.DailyWeather

class GetCurrentWeatherUseCase(
    private val remoteRepository: RemoteRepository,
    private val mapper: CurrentWeatherResponseToDailyWeatherMapper
) {

    suspend operator fun invoke(
        latitude: Double = 0.0,
        longitude: Double = 0.0,
        apiKey: String
    ): DailyWeather = remoteRepository.runCatching {
        getCurrentWeatherData(latitude = latitude, longitude = longitude, apiKey = apiKey)
    }.mapCatching {
        mapper.mappingObjects(it)
    }.onFailure {
        Log.d(this::class.simpleName, "There is an error")
    }.getOrThrow()

}