package com.example.weatherapp.di

import com.example.weatherapp.common.location.LocationManager
import com.example.weatherapp.common.location.LocationManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val locationModule = module {
    factory<LocationManager> { LocationManagerImpl(androidContext()) }

}