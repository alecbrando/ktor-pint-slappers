package com.example.pintslappers.data.repository

import com.example.pintslappers.domain.models.Brewery
import com.example.pintslappers.domain.repository.BreweryRepository
import io.ktor.http.*
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class BreweryRepositoryImp(
    database: CoroutineDatabase
) : BreweryRepository {
    private val breweryCollection = database.getCollection<Brewery>("breweries")
    override suspend fun getBreweries(): List<Brewery> {
        return breweryCollection.find().toList()
    }

    override suspend fun getBrewery(id: Id<Brewery>): Brewery {
        val brewery = breweryCollection.findOne(Brewery::id eq id)
        return brewery ?: throw Exception("Brewery not found")
    }

    override suspend fun addBrewery(brewery: Brewery)  {
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
}