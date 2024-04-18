package com.example.weatherapp.weeklyWeather.mapper

import com.example.domain.mapper.Mapper
import com.example.domain.model.WeeklyWeather
import com.example.domain.util.Constants.DATE_FORMAT_HOUR_MINUTES
import com.example.domain.util.Constants.DATE_FORMAT_ONLY_DAY
import com.example.weatherapp.common.util.DateFormatter.formatDate
import com.example.weatherapp.weeklyWeather.model.WeeklyWeatherUIModel

class WeeklyWeatherToWeeklyWeatherUIModelMapper :
    Mapper<List<WeeklyWeather>, List<WeeklyWeatherUIModel>> {

    override fun mappingObjects(input: List<WeeklyWeather>): List<WeeklyWeatherUIModel> =
        input.map { weeklyWeather: WeeklyWeather ->
            WeeklyWeatherUIModel(
                city = weeklyWeather.city,
                countryCode = weeklyWeather.countryCode,
                day = weeklyWeather.time.formatDate(DATE_FORMAT_ONLY_DAY),
                icon = weeklyWeather.icon,
                maxTemp = weeklyWeather.maxTemp.toInt().toString(),
                minTemp = weeklyWeather.minTemp.toInt().toString(),
                sunset = weeklyWeather.sunset.formatDate(DATE_FORMAT_HOUR_MINUTES),
                sunrise = weeklyWeather.sunrise.formatDate(DATE_FORMAT_HOUR_MINUTES),
                windSpeed = weeklyWeather.windSpeed.toString()
            )
        }

}