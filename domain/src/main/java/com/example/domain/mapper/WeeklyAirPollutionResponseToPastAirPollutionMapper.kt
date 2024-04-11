package com.example.domain.mapper

import com.example.data.model.Pollution
import com.example.data.model.WeeklyAirPollutionsResponse
import com.example.domain.model.PastAirPollution

class WeeklyAirPollutionResponseToPastAirPollutionMapper :
    Mapper<WeeklyAirPollutionsResponse, List<PastAirPollution>> {
    override fun mappingObjects(input: WeeklyAirPollutionsResponse): List<PastAirPollution> =
        input.list.map { pollution: Pollution ->
            PastAirPollution(
                co = pollution.components.co,
                no = pollution.components.no,
                no2 = pollution.components.no2,
                o3 = pollution.components.o3,
                so2 = pollution.components.so2,
                pm25 = pollution.components.pm25,
                pm10 = pollution.components.pm10,
                nh3 = pollution.components.nh3
            )
        }

}