package com.example.plugins.routes.beer

import com.example.domain.models.Beer
import com.example.domain.models.Response
import com.example.domain.repository.BeerRepository
import com.example.pintslappers.domain.models.Brewery
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bson.types.ObjectId
import org.litote.kmongo.id.toId

fun Route.beerRoutes(beerRepository: BeerRepository) {
    get("/beers") {
        call.respondText("Hello, World!")
    }
    get("/beers/{beerId}") {
        val beerId = call.parameters["beerId"]?.let { ObjectId(it).toId<Beer>() }
        if (beerId != null) {
            call.respond(beerRepository.getBeer(beerId))
        } else {
            call.respond(status = HttpStatusCode.BadRequest, message = Response(success = false, message = "Invalid beerId"))
        }
    }
    post("/beers") {
        val beer = call.receive<Beer>()
        beerRepository.addBeer(beer)
        call.respond(status = HttpStatusCode.Created, message = Response(success = true, message = "Successfully added"))
    }
}