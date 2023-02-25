package com.example.pintslappers.domain.models


import com.example.domain.models.Beer
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId

@Serializable
data class Brewery(
    @BsonId
    val id: Id<Brewery> = newId(),
    val name: String? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null,
    val types: String? = null,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)
@Serializable
data class BreweryDto(
    val id: Int,
    val name: String,
    val city: String,
    val state: String,
    val country: String,
    val types: String,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)

//fun Brewery.toDto() = BreweryDto(
//    id = id.toString().toInt(),
//    name = name,
//    city = city,
//    state = state,
//    country = country,
//    types = types,
//    latitude = latitude,
//    longitude = longitude,
//)

fun BreweryDto.toBrewery() = Brewery(
    name = name,
    city = city,
    state = state,
    country = country,
    types = types,
    latitude = latitude,
    longitude = longitude,
)


