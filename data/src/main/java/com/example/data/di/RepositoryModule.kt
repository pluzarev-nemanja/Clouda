package com.example.data.di

import com.example.data.repository.RemoteRepositoryImpl
import com.example.domain.repository.RemoteRepository
import org.koin.dsl.module

val repositoryModule = module{

    single<RemoteRepository> {
        RemoteRepositoryImpl(get())
    }

}