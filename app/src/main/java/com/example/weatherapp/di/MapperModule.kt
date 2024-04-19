package com.example.weatherapp.di

import com.example.weatherapp.airPollution.mapper.PastAirPollutionToAirPollutionUIModelMapper
import com.example.weatherapp.dailyWeather.mapper.DailyWeatherToDailyWeatherUIModelMapper
import com.example.weatherapp.weeklyWeather.mapper.WeeklyWeatherToWeeklyWeatherUIModelMapper
import org.koin.dsl.module

val uiMapperModule = module {

    factory { DailyWeatherToDailyWeatherUIModelMapper() }
    factory { WeeklyWeatherToWeeklyWeatherUIModelMapper() }
    factory { PastAirPollutionToAirPollutionUIModelMapper() }

}