package com.example.domain.mapper

import com.example.data.model.WeeklyWeatherResponse
import com.example.domain.model.WeeklyWeather
import com.example.domain.util.IconExtractor.extractIcon

class WeeklyWeatherResponseToWeeklyWeatherMapper :
    Mapper<WeeklyWeatherResponse, List<WeeklyWeather>> {

    override fun mappingObjects(input: WeeklyWeatherResponse): List<WeeklyWeather> =
        input.list.mapIndexed { index, weatherY ->
            WeeklyWeather(
                minTemp = weatherY.main.minTemp,
                maxTemp = weatherY.main.maxTemp,
                sunrise = input.city.sunrise,
                sunset = input.city.sunset,
                windSpeed = weatherY.wind.speed,
                icon = extractIcon(weatherY.weather[index].icon)
            )
        }

}