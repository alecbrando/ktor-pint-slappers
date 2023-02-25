package com.example.domain.models

import com.example.pintslappers.domain.models.Brewery
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id

@Serializable
data class Beer(
    @Contextual
    @SerialName("_id")
    val id: Id<Beer>,
    val name: String? = null,
    @SerialName("brewery_id")
    val breweryId: Id<Brewery>,
    val state: String? = null,
    val country: String? = null,
    val style: String? = null,
    val availability: String? = null,
    val abv: Double? = null,
    val retired: Boolean? = null
)
