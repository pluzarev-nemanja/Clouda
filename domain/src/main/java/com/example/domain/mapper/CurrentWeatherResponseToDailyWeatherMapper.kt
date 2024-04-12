package com.example.domain.mapper

import com.example.data.model.CurrentWeatherResponse
import com.example.domain.model.DailyWeather
import com.example.domain.util.IconExtractor.extractIcon

class CurrentWeatherResponseToDailyWeatherMapper : Mapper<CurrentWeatherResponse, DailyWeather> {
    override fun mappingObjects(input: CurrentWeatherResponse): DailyWeather =
        DailyWeather(
            location = input.name,
            dateAndTime = input.time,
            currentTemp = input.main.temp,
            feelsLike = input.main.feelsLike,
            icon = extractIcon(input.weather.first().icon),
            weatherDescription = input.weather.first().description
        )


}