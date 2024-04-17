package com.example.domain.useCases.getWeeklyAirPollution

import android.util.Log
import com.example.data.repository.RemoteRepository
import com.example.domain.mapper.WeeklyAirPollutionResponseToPastAirPollutionMapper
import com.example.domain.model.PastAirPollution

class GetWeeklyAirPollutionUseCase(
    private val remoteRepository: RemoteRepository,
    private val mapper: WeeklyAirPollutionResponseToPastAirPollutionMapper
) {

    suspend operator fun invoke(
        latitude: Double = 0.0,
        longitude: Double = 0.0,
        startingDay: Long = 0L,
        endingDay: Long = 0L
    ): List<PastAirPollution> = remoteRepository.runCatching {
            getWeeklyAirPollutionData(
                latitude = latitude,
                longitude = longitude,
                startingDay = startingDay,
                endingDay = endingDay,
            )
        }
        .mapCatching {
            mapper.mappingObjects(it)
        }
        .onFailure {
            Log.d(this::class.simpleName, "There is an error")
        }.getOrThrow()

}