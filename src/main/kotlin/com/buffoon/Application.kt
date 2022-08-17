package com.buffoon

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.buffoon.plugins.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        configureHTTP()
        configureSerialization()
        configureRouting()
        configureDB()
    }.start(wait = true)
}
