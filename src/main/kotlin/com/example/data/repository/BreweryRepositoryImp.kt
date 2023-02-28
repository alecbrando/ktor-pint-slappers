package com.example.pintslappers.data.repository

import com.example.domain.models.SearchRequest
import com.example.pintslappers.domain.models.Brewery
import com.example.pintslappers.domain.models.BreweryWithBeers
import com.example.pintslappers.domain.repository.BreweryRepository
import com.mongodb.client.model.Aggregates.*
import com.mongodb.client.model.BsonField
import com.mongodb.client.model.Filters.and
import com.mongodb.client.model.Filters.geoWithinCenterSphere
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.aggregate
import org.litote.kmongo.eq
import org.litote.kmongo.from

class BreweryRepositoryImp(
    database: CoroutineDatabase
) : BreweryRepository {
    private val breweryCollection = database.getCollection<Brewery>("breweries")

    override suspend fun getBreweries(page: Int, pageSize: Int): List<Brewery> {
        return try {
            breweryCollection.find().limit(pageSize).skip(page * pageSize).toList()
        } catch (e: Exception) {
            throw Exception("${e.localizedMessage}")
        }
    }

    override suspend fun getBreweriesNearMe(searchRequest: SearchRequest): List<Brewery> {
       return getBreweriesWithinDistance(searchRequest)
    }

    override suspend fun getBrewery(id: Id<Brewery>): BreweryWithBeers {
        try {
            val brewery = breweryCollection.aggregate<BreweryWithBeers>(
                match(Brewery::id eq id),
                lookup(
                    "beers",
                    Brewery::beers.name,
                    "_id",
                    "beers"
                ),
                unwind("\$beers"),
                group(
                    Brewery::id,
                    BsonField("name", "\$first" from Brewery::name),
                    BsonField("city", "\$first" from Brewery::city),
                    BsonField("state", "\$first" from Brewery::state),
                    BsonField("country", "\$first" from Brewery::country),
                    BsonField("coordinates", "\$first" from Brewery::coordinates),
                    BsonField("types", "\$first" from Brewery::types),
                    BsonField("beers", "\$addToSet" from Brewery::beers)
                )
            ).first()
            return brewery ?: throw Exception("Brewery not found")
        } catch (e: Exception) {
            throw Exception("${e.localizedMessage}")
        }
    }

    override suspend fun addBrewery(brewery: Brewery) {
        try {
            breweryCollection.insertOne(brewery)
        } catch (e: Exception) {
            throw Exception("Brewery already exists")
        }
    }

    override suspend fun updateBrewery(brewery: Brewery) {
        breweryCollection.updateOne(Brewery::id eq brewery.id, brewery)
    }

    override suspend fun deleteBrewery(id: Id<Brewery>) {
        breweryCollection.deleteOne(Brewery::id eq id)
    }

    private suspend fun getBreweriesWithinDistance(searchRequest: SearchRequest): List<Brewery> {
        try {
            // Define the user's location and the maximum distance to search
            val userLatitude = searchRequest.lat
            val userLongitude = searchRequest.long
            val maxDistanceInKilometers = searchRequest.distanceInKm

            val query = and(
                geoWithinCenterSphere("coordinates", userLongitude, userLatitude, maxDistanceInKilometers / 6371.0)
            )

            // Execute the query and return the results
            return breweryCollection.find(query).toList()
        } catch (e: Exception) {
            throw Exception("${e.localizedMessage}")
        }
    }
}