package com.example.domain.di

import com.example.domain.mapper.CurrentWeatherResponseToDailyWeatherMapper
import com.example.domain.mapper.WeeklyAirPollutionResponseToPastAirPollutionMapper
import com.example.domain.mapper.WeeklyWeatherResponseToWeeklyWeatherMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { WeeklyWeatherResponseToWeeklyWeatherMapper() }
    factory { CurrentWeatherResponseToDailyWeatherMapper() }
    factory { WeeklyAirPollutionResponseToPastAirPollutionMapper() }
}