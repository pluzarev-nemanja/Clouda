package com.example.weatherapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.example.domain.di.modules

class Application: Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(modules)
        }
    }
}