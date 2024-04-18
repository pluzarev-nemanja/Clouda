package com.example.weatherapp.weeklyWeather.mapper

import com.example.domain.mapper.Mapper
import com.example.domain.model.WeeklyWeather
import com.example.domain.util.Constants.DATE_FORMAT_HOUR_MINUTES
import com.example.domain.util.Constants.DATE_FORMAT_ONLY_DAY
import com.example.weatherapp.weeklyWeather.model.WeeklyWeatherUIModel
import java.text.SimpleDateFormat

class WeeklyWeatherToWeeklyWeatherUIModelMapper :
    Mapper<List<WeeklyWeather>, List<WeeklyWeatherUIModel>> {

    override fun mappingObjects(input: List<WeeklyWeather>): List<WeeklyWeatherUIModel> =
        input.map { weeklyWeather: WeeklyWeather ->
            WeeklyWeatherUIModel(
                city = weeklyWeather.city,
                countryCode = weeklyWeather.countryCode,
                day = SimpleDateFormat(DATE_FORMAT_ONLY_DAY).format(weeklyWeather.time * 1000),
                icon = weeklyWeather.icon,
                maxTemp = weeklyWeather.maxTemp.toInt().toString(),
                minTemp = weeklyWeather.minTemp.toInt().toString(),
                sunset = SimpleDateFormat(DATE_FORMAT_HOUR_MINUTES).format(weeklyWeather.sunset * 1000),
                sunrise = SimpleDateFormat(DATE_FORMAT_HOUR_MINUTES).format(weeklyWeather.sunrise * 1000),
                windSpeed = weeklyWeather.windSpeed.toString()
            )
        }

}