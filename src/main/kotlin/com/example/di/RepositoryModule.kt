package com.example.pintslappers.di

import com.example.pintslappers.data.repository.RepositoryImp
import com.example.pintslappers.domain.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> {
        RepositoryImp(get())
    }
}