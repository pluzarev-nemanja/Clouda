package com.example.weatherapp.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherapp.airPollution.viewModel.AirPollutionViewModel
import com.example.weatherapp.common.model.LatLong
import com.example.weatherapp.dailyWeather.viewModel.HomeViewModel
import com.example.weatherapp.weeklyWeather.viewModel.WeeklyWeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val viewModelModule = module {
    viewModel<HomeViewModel> {(latLong : LatLong) ->
        HomeViewModel(
            latLong,
            get(),
            get()
        )
    }
    viewModel<AirPollutionViewModel> {
        AirPollutionViewModel(get(), get())
    }
    viewModel<WeeklyWeatherViewModel> {
        WeeklyWeatherViewModel(get(), get())
    }
}