package com.example.plugins.routes.brewery

import com.example.domain.models.Response
import com.example.pintslappers.domain.models.Brewery
import com.example.pintslappers.domain.models.BreweryDto
import com.example.pintslappers.domain.models.toBrewery
import com.example.pintslappers.domain.models.toDto
import com.example.pintslappers.domain.repository.BreweryRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.id.toId

fun Route.breweryRoutes(breweryRepository: BreweryRepository) {
    post("/brewery") {
        try {
            val brewery = call.receive<BreweryDto>()
            breweryRepository.addBrewery(brewery.toBrewery())
            call.respond(
                status = HttpStatusCode.Created,
                message = Response(success = true, message = "Successfully added")
            )
        } catch (e: CannotTransformContentToTypeException) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = Response(success = false, message = e.localizedMessage)
            )
        }
    }
    get("/brewery") {
        call.respond(breweryRepository.getBreweries())
    }
    get("/brewery/{breweryId}") {
        val breweryId = call.parameters["breweryId"]?.let { ObjectId(it).toId<Brewery>() }
        if (breweryId != null) {
//            call.respond(breweryRepository.getBrewery(breweryId).toDto())
            call.respond(breweryRepository.getBrewery(breweryId))
        } else {
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = Response(success = false, message = "Invalid breweryId")
            )
        }
    }
}