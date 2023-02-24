package com.example.plugins.routes.beer

import com.example.domain.repository.BeerRepository
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.beerRoutes(beerRepository: BeerRepository) {
    get("/beers") {
        call.respondText("Hello, World!")
    }
}