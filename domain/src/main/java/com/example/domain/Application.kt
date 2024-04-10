package com.example.domain

import android.app.Application
import com.example.domain.di.mapperModule
import com.example.domain.di.repositoryModule
import com.example.domain.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(useCaseModule, repositoryModule, mapperModule)
        }
    }
}