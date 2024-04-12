package com.example.domain.di

import com.example.domain.useCases.UseCases
import com.example.domain.useCases.getCurrentWeather.GetCurrentWeatherUseCase
import com.example.domain.useCases.getWeeklyAirPollution.GetWeeklyAirPollutionUseCase
import com.example.domain.useCases.getWeeklyWeather.GetWeeklyWeatherUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        UseCases(
            getCurrentWeather = GetCurrentWeatherUseCase(get(),get()),
            getWeeklyAirPollution = GetWeeklyAirPollutionUseCase(get(),get()),
            getWeeklyWeather = GetWeeklyWeatherUseCase(get(),get())
        )
    }
}