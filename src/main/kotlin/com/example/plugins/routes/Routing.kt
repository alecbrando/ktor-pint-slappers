package com.example.plugins.routes

import com.example.domain.repository.BeerRepository
import com.example.pintslappers.domain.repository.BreweryRepository
import com.example.plugins.routes.beer.beerRoutes
import com.example.plugins.routes.brewery.breweryRoutes
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import kotlinx.serialization.Serializable
import org.koin.java.KoinJavaComponent.inject

fun Application.configureRouting() {
    val beerRepository: BeerRepository by inject<BeerRepository>(BeerRepository::class.java)
    val breweryRepository: BreweryRepository by inject<BreweryRepository>(BreweryRepository::class.java)

    routing {
        beerRoutes(beerRepository)
        breweryRoutes(breweryRepository)
        get("/") {
            call.respondText("Hello World!")
        }
    }
}