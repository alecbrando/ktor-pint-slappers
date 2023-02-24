package com.example.plugins.routes.brewery

import com.example.domain.models.Response
import com.example.pintslappers.domain.models.Brewery
import com.example.pintslappers.domain.repository.BreweryRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.breweryRoutes(breweryRepository: BreweryRepository) {
    post("/brewery") {
        val brewery = call.receive<Brewery>()
        breweryRepository.addBrewery(brewery)
        call.respond(status = HttpStatusCode.Created, message = Response(success = true, message = "Successfully added"))
    }
    get("/brewery") {
        call.respond(breweryRepository.getBreweries())
    }
}