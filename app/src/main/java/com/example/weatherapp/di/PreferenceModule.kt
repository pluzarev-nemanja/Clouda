package com.example.weatherapp.di

import com.example.weatherapp.common.pref.PreferencesManager
import com.example.weatherapp.common.pref.PreferencesManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferenceModule = module{

    factory<PreferencesManager>{ PreferencesManagerImpl(androidContext()) }
}