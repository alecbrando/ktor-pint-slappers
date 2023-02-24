package com.example.pintslappers.di

import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mongoModule = module {
    single {
        val client = KMongo.createClient().coroutine
        val database = client.getDatabase("pintslappers")
        database
    }
}