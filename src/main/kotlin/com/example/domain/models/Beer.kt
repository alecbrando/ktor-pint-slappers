package com.example.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Beer(
    val id: Int? = null,
    val name: String? = null,
    @SerialName("brewery_id")
    val breweryId: Int? = null,
    val state: String? = null,
    val country: String? = null,
    val style: String? = null,
    val availability: String? = null,
    val abv: Double? = null,
    val retired: Boolean? = null
)
