package com.example.domain.mapper

import com.example.data.model.WeatherY
import com.example.data.model.WeeklyWeatherResponse
import com.example.domain.model.WeeklyWeather

class WeeklyWeatherResponseToWeeklyWeatherMapper :
    Mapper<WeeklyWeatherResponse, List<WeeklyWeather>> {

    override fun mappingObjects(input: WeeklyWeatherResponse): List<WeeklyWeather> =
        input.list.map { weatherY: WeatherY ->
            WeeklyWeather(
                minTemp = weatherY.main.minTemp,
                maxTemp = weatherY.main.maxTemp,
                sunrise = input.city.sunrise,
                sunset = input.city.sunset,
                windSpeed = weatherY.wind.speed,
                icon = weatherY.weather[0].icon
            )
        }

}