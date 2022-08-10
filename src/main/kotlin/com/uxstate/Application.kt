package com.uxstate

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun main(args:Array<String>):Unit = io.ktor.server.netty.EngineMain.main(args)


//Ext fxn on the Application class


//Our engineMain will use this module to respond to clients
fun Application.module( ){

    routing {
        //server root path
        get (path = "/"){

            //define how to respond to user's requests
            call.respondText("Hello Tonnie,")
        }
    }

}