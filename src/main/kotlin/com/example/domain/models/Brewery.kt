package com.example.pintslappers.domain.models


import com.example.domain.models.Beer
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.id.toId
import org.litote.kmongo.newId

data class Brewery(
    @BsonId
    val id: Id<Brewery> = newId(),
    val name: String? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null,
    val types: String? = null,
    val coordinates: List<Double>? = null,
    val beers: Set<Id<Beer>> = emptySet()
)

data class BreweryWithBeers(
    @BsonId
    val id: Id<Brewery> = newId(),
    val name: String? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null,
    val types: String? = null,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val beers: List<Beer> = emptyList()
)

data class BreweryDto(
    val id: String? = null,
    val name: String? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null,
    val types: String? = null,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val beers: List<String> = emptyList()
)

fun Brewery.toDto() = BreweryDto(
    id = id.toString(),
    name = name,
    city = city,
    state = state,
    country = country,
    types = types,
    latitude = coordinates?.get(1) ?: 0.0,
    longitude = coordinates?.get(0) ?: 0.0,
    beers = beers.map { it.toString() }
)

fun BreweryDto.toBrewery() = Brewery(
    id = ObjectId(id).toId(),
    name = name,
    city = city,
    state = state,
    country = country,
    types = types,
    coordinates =  listOf(longitude, latitude),
    beers = if (beers.isEmpty() || (beers.size == 1 && beers[0] == "")) {
        emptySet()
    } else {
        beers.map { ObjectId(it).toId<Beer>() }.toSet()
    }
)


