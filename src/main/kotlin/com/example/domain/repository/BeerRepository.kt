package com.example.domain.repository

import com.example.domain.models.Beer

interface BeerRepository {
    suspend fun getBeers(): List<Beer>
    suspend fun getBeer(id: Int): Beer
    suspend fun addBeer(beer: Beer)
    suspend fun updateBeer(beer: Beer)
    suspend fun deleteBeer(id: Int)
}