package com.example.domain.useCases.getWeeklyAirPollution

import com.example.domain.model.PastAirPollution
import com.example.data.repository.RemoteRepository
import com.example.domain.mapper.CurrentWeatherResponseToDailyWeatherMapper
import com.example.domain.mapper.WeeklyAirPollutionResponseToPastAirPollutionMapper

class GetWeeklyAirPollutionUseCase(
    private val remoteRepository: RemoteRepository,
    private val mapper: WeeklyAirPollutionResponseToPastAirPollutionMapper
) {

    suspend operator fun invoke(
        latitude: Double = 0.0,
        longitude: Double = 0.0,
        startingDay: Int = 0,
        endingDay: Int = 0,
        apiKey: String
    ): List<PastAirPollution> {
        val model = remoteRepository.getWeeklyAirPollutionData(
            latitude = latitude,
            longitude = longitude,
            startingDay = startingDay,
            endingDay = endingDay,
            apiKey = apiKey
        )
        return mapper.mappingObjects(model)
    }
}