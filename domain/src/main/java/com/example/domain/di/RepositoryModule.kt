package com.example.domain.di

import com.example.data.repository.RemoteRepository
import com.example.domain.repository.RemoteRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<RemoteRepository> {
        RemoteRepositoryImpl(get())
    }

}