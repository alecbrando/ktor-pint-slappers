package com.example.pintslappers.di

import com.example.pintslappers.domain.models.Brewery
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.dsl.module

val realmModule = module {
    single {
        val config = RealmConfiguration.create(schema = setOf(Brewery::class))
        Realm.open(config)
    }
}