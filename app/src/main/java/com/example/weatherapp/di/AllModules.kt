package com.example.weatherapp.di

import com.example.domain.di.modules

val allModules = modules.plus(viewModelModule).plus(uiMapperModule)