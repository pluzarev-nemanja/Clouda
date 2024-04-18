package com.example.weatherapp.di

import com.example.weatherapp.dailyWeather.mapper.DailyWeatherToDailyWeatherUIModelMapper
import org.koin.dsl.module

val uiMapperModule = module {

    factory { DailyWeatherToDailyWeatherUIModelMapper() }

}