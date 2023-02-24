package com.example.plugins

import com.example.pintslappers.di.mongoModule
import com.example.pintslappers.di.repositoryModule
import io.ktor.server.application.*
import org.koin.core.context.startKoin

fun Application.configureKoin() {
    startKoin{
      modules(listOf(mongoModule, repositoryModule))
    }
}