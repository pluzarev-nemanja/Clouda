package com.example.weatherapp.airPollution.mapper

import com.example.domain.mapper.Mapper
import com.example.domain.model.PastAirPollution
import com.example.domain.util.Constants.DATE_FORMAT_DAY_DATE_TIME
import com.example.weatherapp.airPollution.model.AirPollutionUIModel
import java.text.SimpleDateFormat

class PastAirPollutionToAirPollutionUIModelMapper :
    Mapper<List<PastAirPollution>, List<AirPollutionUIModel>> {

    override fun mappingObjects(input: List<PastAirPollution>): List<AirPollutionUIModel> =
        input.map { pastAirPollution: PastAirPollution ->
            AirPollutionUIModel(
                date = SimpleDateFormat(DATE_FORMAT_DAY_DATE_TIME).format(pastAirPollution.time * 1000),
                carbonMonoxide = pastAirPollution.co.toString(),
                ammonia = pastAirPollution.nh3.toString(),
                nitrogenDioxide = pastAirPollution.no2.toString(),
                nitrogenMonoxide = pastAirPollution.no.toString(),
                ozone = pastAirPollution.o3.toString(),
                pm10 = pastAirPollution.pm10.toString(),
                pm25 = pastAirPollution.pm25.toString(),
                sulphur = pastAirPollution.so2.toString()
            )
        }
}