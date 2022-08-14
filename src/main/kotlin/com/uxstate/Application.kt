package com.uxstate

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*

fun main(){

    embeddedServer(factory = Netty, port = 8080){
module()
        install(ContentNegotiation){

            json()
        }
    }.start(wait = true)
}


//Ext fxn on the Application class


//Our engineMain will use this module to respond to clients
fun Application.module( ){



    routing {

        //get {} is an extension function on Route class under the Routing plugin
        //server root path - 1st endpoint
        get (path = "/"){

            //define how to respond to user's requests
            call.respondText("Hello ")
        }

        //2nd end point

        get(path = "/users/{username}") {
            val username = call.parameters["username"]
            val header = call.request.headers["Connection"]

            if (username =="Admin"){

                call.response.header("CustomHeader", "Admin")
                call.respond(message = "Hello Admin", status = HttpStatusCode.OK)
            }
            call.respondText("Greeting, $username with header: $header")
        }

        get(path = "/user"){

            val name = call.request.queryParameters["name"]
            val age = call.request.queryParameters["age"]

            call.respond("Hey, my name is $name, I am $age years old.")
        }

        get (path = "/person"){

            try {
                val voter = Voter(name = "Tonnie", stream = 13)

                call.respond(message = voter, status = HttpStatusCode.OK)
            }catch (e:Exception ){
                call.respond(message = "${e.message}", status = HttpStatusCode.BadRequest)

            }
        }


        get(path = "/redirect"){

            call.respondRedirect(url = "/moved", permanent = false)
        }

        get(path = "/moved"){

            call.respond(message = "Redirected successfully", status = HttpStatusCode.OK)
        }


        static(remotePath = "assets") {

            resources("static")
        }

        get(path = "/welcome") {

            call.respondHtml{}
        }


    }

}



@Serializable
 data class Voter(val name:String, val stream:Int)