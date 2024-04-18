package com.example.weatherapp.dailyWeather.mapper

import com.example.domain.mapper.Mapper
import com.example.domain.model.DailyWeather
import com.example.weatherapp.dailyWeather.model.DailyWeatherUIModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class DailyWeatherToDailyWeatherUIModelMapper : Mapper<DailyWeather, DailyWeatherUIModel> {

    override fun mappingObjects(input: DailyWeather): DailyWeatherUIModel =
        DailyWeatherUIModel(
            icon = input.icon,
            currentTemp = input.currentTemp.toInt().toString(),
            location = input.location,
            feelsLikeTemp = input.feelsLike.toInt().toString(),
            detailDescription = input.weatherDescription,
            currentTime = LocalDateTime.now().format(
                DateTimeFormatter.ofLocalizedDateTime(
                    FormatStyle.SHORT
                )
            )
        )

}

