package com.example.domain.useCases.getWeeklyWeather

import com.example.domain.model.WeeklyWeather
import com.example.data.repository.RemoteRepository
import com.example.domain.mapper.WeeklyAirPollutionResponseToPastAirPollutionMapper
import com.example.domain.mapper.WeeklyWeatherResponseToWeeklyWeatherMapper

class GetWeeklyWeatherUseCase(
    private val remoteRepository: RemoteRepository,
    private val mapper: WeeklyWeatherResponseToWeeklyWeatherMapper

) {

    suspend operator fun invoke(
        latitude: Double = 0.0,
        longitude: Double = 0.0,
        apiKey: String
    ): List<WeeklyWeather> {
        val model = remoteRepository.getWeeklyWeatherData(
            latitude = latitude,
            longitude = longitude,
            apiKey = apiKey
        )
        return mapper.mappingObjects(model)
    }
}