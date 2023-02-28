package com.example.plugins.routes.beer

import com.example.domain.models.*
import com.example.domain.repository.BeerRepository
import com.example.pintslappers.domain.models.Brewery
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bson.types.ObjectId
import org.litote.kmongo.id.toId

fun Route.beerRoutes(beerRepository: BeerRepository) {
    get("/beers") {
        try {
            val page = call.parameters["page"]?.toIntOrNull() ?: 1
            val pageSize = call.parameters["pageSize"]?.toIntOrNull() ?: 20
            val data = beerRepository.getBeers(page, pageSize)
            call.respond(PaginationResponse(data = data, page, pageSize))
        } catch (e: Exception) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = Response(success = false, message = e.localizedMessage)
            )
        }
    }
    get("/beers/{beerId}") {
        val beerId = call.parameters["beerId"]?.let { ObjectId(it).toId<Beer>() }
        if (beerId != null) {
            call.respond(beerRepository.getBeer(beerId))
        } else {
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = Response(success = false, message = "Invalid beerId")
            )
        }
    }
    post("/beer") {
        try {
            val beer = call.receive<BeerDto>()
            beerRepository.addBeer(beer.toBeer())
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
}