package com.example.domain.di

import com.example.data.model.CurrentWeatherResponse
import com.example.data.model.WeeklyAirPollutionsResponse
import com.example.data.model.WeeklyWeatherResponse
import com.example.domain.mapper.CurrentWeatherResponseToDailyWeatherMapper
import com.example.domain.mapper.Mapper
import com.example.domain.mapper.WeeklyAirPollutionResponseToPastAirPollutionMapper
import com.example.domain.mapper.WeeklyWeatherResponseToWeeklyWeatherMapper
import com.example.domain.model.DailyWeather
import com.example.domain.model.PastAirPollution
import com.example.domain.model.WeeklyWeather
import org.koin.dsl.module

val mapperModule = module {
    factory<Mapper<WeeklyWeatherResponse, List<WeeklyWeather>>> {
        WeeklyWeatherResponseToWeeklyWeatherMapper()
    }
    factory<Mapper<WeeklyAirPollutionsResponse, List<PastAirPollution>>> {
        WeeklyAirPollutionResponseToPastAirPollutionMapper()
    }
    factory<Mapper<CurrentWeatherResponse, DailyWeather>> {
        CurrentWeatherResponseToDailyWeatherMapper()
    }
}