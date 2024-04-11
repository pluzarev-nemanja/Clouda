package com.example.domain.di

import com.example.data.di.remoteModule

val modules = mapperModule.plus(repositoryModule).plus(useCaseModule).plus(remoteModule)