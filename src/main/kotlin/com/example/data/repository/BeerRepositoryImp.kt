package com.example.data.repository

import com.example.domain.models.Beer
import com.example.domain.models.BeerWithBrewery
import com.example.domain.repository.BeerRepository
import com.example.pintslappers.domain.models.BreweryWithBeers
import com.mongodb.client.model.Aggregates.*
import com.mongodb.client.model.BsonField
import io.ktor.http.*
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.aggregate
import org.litote.kmongo.eq
import org.litote.kmongo.from

class BeerRepositoryImp(
    database: CoroutineDatabase
) : BeerRepository {
    private val beerCollection = database.getCollection<Beer>("beers")

    override suspend fun getBeers(): List<Beer> {
        return beerCollection.find().toList()
    }

    override suspend fun getBeer(id: Id<Beer>): BeerWithBrewery {
        try {
            val beer = beerCollection.aggregate<BeerWithBrewery>(
                match(Beer::id eq id),
                lookup("breweries", Beer::breweryId.name, "_id", "brewery"),
                unwind("\$brewery"),
                group(
                    Beer::id,
                    BsonField("name", "\$first" from Beer::name),
                    BsonField("brewery", "\$first" from BeerWithBrewery::brewery),
                    BsonField("state", "\$first" from BeerWithBrewery::state),
                    BsonField("country", "\$first" from BeerWithBrewery::country),
                    BsonField("style", "\$first" from BeerWithBrewery::style),
                    BsonField("availability", "\$first" from BeerWithBrewery::availability),
                    BsonField("abv", "\$first" from BeerWithBrewery::abv),
                    BsonField("retired", "\$first" from BeerWithBrewery::retired),
                )
            ).first()
            return beer ?: throw Exception("Beer not found")
        } catch (e: Exception) {
            throw Exception("${e.localizedMessage}")
        }
    }

    override suspend fun addBeer(beer: Beer) {
        beerCollection.insertOne(beer)
    }

    override suspend fun updateBeer(beer: Beer) {
        beerCollection.updateOne(Beer::id eq beer.id, beer)
    }

    override suspend fun deleteBeer(id: Id<Beer>) {
        beerCollection.deleteOne(Beer::id eq id)
    }
}