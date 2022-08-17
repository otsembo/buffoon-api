package com.buffoon

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.buffoon.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureHTTP()
        configureSerialization()
        configureRouting()
        configureDB()
    }.start(wait = true)
}
