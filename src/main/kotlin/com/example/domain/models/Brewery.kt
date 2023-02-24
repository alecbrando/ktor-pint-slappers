package com.example.pintslappers.domain.models


import kotlinx.serialization.Serializable

@Serializable
data class Brewery(
    val id: Int? = null,
    val name: String? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null,
    val types: String? = null,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)


