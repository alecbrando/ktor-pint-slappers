package com.example.domain.models

import com.example.pintslappers.domain.models.Brewery
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.id.toId
import org.litote.kmongo.newId

data class Beer(
    @BsonId
    val id: Id<Beer> = newId(),
    val name: String? = null,
    @SerialName("brewery_id")
    val breweryId: Id<Brewery>? = null,
    val state: String? = null,
    val country: String? = null,
    val style: String? = null,
    val availability: String? = null,
    val abv: Double? = null,
    val retired: Boolean? = null
)

data class BeerWithBrewery(
    @BsonId
    val id: Id<Beer> = newId(),
    val name: String? = null,
    val brewery: Brewery? = null,
    val state: String? = null,
    val country: String? = null,
    val style: String? = null,
    val availability: String? = null,
    val abv: Double? = null,
    val retired: Boolean? = null
)

data class BeerDto(
    val id: String? = null,
    val name: String? = null,
    val breweryId: String? = null,
    val state: String? = null,
    val country: String? = null,
    val style: String? = null,
    val availability: String? = null,
    val abv: Double? = null,
    val retired: Boolean? = null
)

fun Beer.toDto() = BeerDto(
    id = id.toString(),
    name = name,
    breweryId = breweryId.toString(),
    state = state,
    country = country,
    style = style,
    availability = availability,
    abv = abv,
    retired = retired
)

fun BeerDto.toBeer() = Beer(
    id = ObjectId(id).toId(),
    name = name,
    breweryId = ObjectId(breweryId).toId<Brewery>(),
    state = state,
    country = country,
    style = style,
    availability = availability,
    abv = abv,
    retired = retired
)