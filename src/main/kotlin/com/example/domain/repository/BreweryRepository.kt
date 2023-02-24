package com.example.pintslappers.domain.repository

import com.example.pintslappers.domain.models.Brewery

interface BreweryRepository {
    suspend fun getBreweries(): List<Brewery>
    suspend fun getBrewery(id: Int): Brewery
    suspend fun addBrewery(brewery: Brewery)
    suspend fun updateBrewery(brewery: Brewery)
    suspend fun deleteBrewery(id: Int)
}