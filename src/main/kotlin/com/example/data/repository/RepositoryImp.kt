package com.example.pintslappers.data.repository

import com.example.pintslappers.domain.models.Brewery
import com.example.pintslappers.domain.repository.Repository
import io.realm.kotlin.Realm

class RepositoryImp(
    private val realm: Realm
): Repository {
    override fun getBreweries(): List<Brewery> {
        TODO("Not yet implemented")
    }

    override fun getBrewery(id: String): Brewery {
        TODO("Not yet implemented")
    }

    override fun addBrewery(brewery: Brewery) {
       realm.writeBlocking {
           copyToRealm(brewery)
       }
    }

    override fun updateBrewery(brewery: Brewery) {
        TODO("Not yet implemented")
    }

    override fun deleteBrewery(id: String) {
        TODO("Not yet implemented")
    }
}