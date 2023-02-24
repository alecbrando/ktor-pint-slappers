package com.example.data.repository

import com.example.domain.models.Beer
import com.example.domain.repository.BeerRepository
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class BeerRepositoryImp(
    database: CoroutineDatabase
) : BeerRepository {
    private val beerCollection = database.getCollection<Beer>("beers")

    override suspend fun getBeers(): List<Beer> {
        return beerCollection.find().toList()
    }

    override suspend fun getBeer(id: Int): Beer {
        val beer = beerCollection.findOne(Beer::id eq id)
        return beer ?: throw Exception("Beer not found")
    }

    override suspend fun addBeer(beer: Beer) {
        beerCollection.insertOne(beer)
    }

    override suspend fun updateBeer(beer: Beer) {
        beerCollection.updateOne(Beer::id eq beer.id, beer)
    }

    override suspend fun deleteBeer(id: Int) {
        beerCollection.deleteOne(Beer::id eq id)
    }
}