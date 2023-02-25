package com.example.domain.repository

import com.example.domain.models.Beer
import org.litote.kmongo.Id

interface BeerRepository {
    suspend fun getBeers(): List<Beer>
    suspend fun getBeer(id: Id<Beer>): Beer
    suspend fun addBeer(beer: Beer)
    suspend fun updateBeer(beer: Beer)
    suspend fun deleteBeer(id: Id<Beer>)

}