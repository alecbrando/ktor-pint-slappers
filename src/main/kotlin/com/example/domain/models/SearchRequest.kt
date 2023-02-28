package com.example.domain.models

data class SearchRequest(
    val lat: Double,
    val long: Double,
    val distanceInKm: Double
)
