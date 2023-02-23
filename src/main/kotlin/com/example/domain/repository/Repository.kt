package com.example.pintslappers.domain.repository

import com.example.pintslappers.domain.models.Brewery

interface Repository {
    fun getBreweries(): List<Brewery>
    fun getBrewery(id: String): Brewery
    fun addBrewery(brewery: Brewery)
    fun updateBrewery(brewery: Brewery)
    fun deleteBrewery(id: String)
}