package com.example.weatherapp.di

import com.example.weatherapp.airPollution.viewModel.AirPollutionViewModel
import com.example.weatherapp.dailyWeather.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<HomeViewModel>{
        HomeViewModel(get())
    }
    viewModel<AirPollutionViewModel>{
        AirPollutionViewModel(get())
    }
}