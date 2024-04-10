package com.example.domain.mapper

import com.example.data.model.CurrentWeatherResponse
import com.example.domain.model.DailyWeather

class CurrentWeatherResponseToDailyWeatherMapper : Mapper<CurrentWeatherResponse,DailyWeather> {
    override fun mappingObjects(input: CurrentWeatherResponse): DailyWeather {
        return DailyWeather(
            location = input.name,
            dateAndTime = input.time,
            currentTemp = input.main.temp,
            feelsLike = input.main.feelsLike,
            icon = input.weather[0].icon,
            weatherDescription = input.weather[0].icon
        )
    }
}