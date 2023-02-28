package com.example.plugins.routes.brewery

import com.example.domain.models.PaginationResponse
import com.example.domain.models.Response
import com.example.domain.models.SearchRequest
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
        } catch (e: Exception) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = Response(success = false, message = e.localizedMessage)
            )
        }
    }
    get("/breweries/nearby"){
        val lat = call.parameters["lat"]?.toDoubleOrNull() ?: throw IllegalArgumentException("lat parameter is missing or invalid")
        val long = call.parameters["long"]?.toDoubleOrNull() ?: throw IllegalArgumentException("long parameter is missing or invalid")
        val distance = call.parameters["distance"]?.toDoubleOrNull() ?: throw IllegalArgumentException("distance parameter is missing or invalid")

        val searchRequest = SearchRequest(lat, long, distance)

        val breweries = breweryRepository.getBreweriesNearMe(searchRequest)
        call.respond(breweries)
    }
    get("/breweries") {
        try {
            val page = call.parameters["page"]?.toIntOrNull() ?: 1
            val pageSize = call.parameters["pageSize"]?.toIntOrNull() ?: 20
            val data = breweryRepository.getBreweries(page, pageSize)
            call.respond(PaginationResponse(data = data, page, pageSize))
        } catch (e: Exception) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = Response(success = false, message = e.localizedMessage)
            )
        }
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