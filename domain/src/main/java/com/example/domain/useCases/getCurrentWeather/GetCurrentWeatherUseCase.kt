package com.example.domain.useCases.getCurrentWeather

import com.example.domain.model.DailyWeather
import com.example.data.repository.RemoteRepository
import com.example.domain.mapper.CurrentWeatherResponseToDailyWeatherMapper

class GetCurrentWeatherUseCase(
    private val remoteRepository: RemoteRepository,
    private val mapper: CurrentWeatherResponseToDailyWeatherMapper
) {

    suspend operator fun invoke(
        latitude: Double = 0.0,
        longitude: Double = 0.0,
        apiKey: String
    ): DailyWeather {
        val model = remoteRepository.getCurrentWeatherData(
            latitude = latitude,
            longitude = longitude,
            apiKey = apiKey
        )
        return mapper.mappingObjects(model)
    }
}