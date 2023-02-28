package com.example.domain.repository

import com.example.domain.models.Beer
import com.example.domain.models.BeerWithBrewery
import org.litote.kmongo.Id

interface BeerRepository {
    suspend fun getBeers(page: Int, pageSize: Int): List<Beer>
    suspend fun getBeer(id: Id<Beer>): BeerWithBrewery
    suspend fun addBeer(beer: Beer)
    suspend fun updateBeer(beer: Beer)
    suspend fun deleteBeer(id: Id<Beer>)

}