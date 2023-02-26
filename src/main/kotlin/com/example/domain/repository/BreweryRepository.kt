package com.example.pintslappers.domain.repository

import com.example.pintslappers.domain.models.Brewery
import com.example.pintslappers.domain.models.BreweryWithBeers
import org.litote.kmongo.Id

interface BreweryRepository {
    suspend fun getBreweries(): List<Brewery>
    suspend fun getBrewery(id: Id<Brewery>): BreweryWithBeers
    suspend fun addBrewery(brewery: Brewery)
    suspend fun updateBrewery(brewery: Brewery)
    suspend fun deleteBrewery(id: Id<Brewery>)
}