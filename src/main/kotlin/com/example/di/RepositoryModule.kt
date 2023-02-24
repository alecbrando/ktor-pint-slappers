package com.example.pintslappers.di

import com.example.data.repository.BeerRepositoryImp
import com.example.domain.repository.BeerRepository
import com.example.pintslappers.data.repository.BreweryRepositoryImp
import com.example.pintslappers.domain.repository.BreweryRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<BreweryRepository> {
        BreweryRepositoryImp(get())
    }
    single<BeerRepository> {
        BeerRepositoryImp(get())
    }
}