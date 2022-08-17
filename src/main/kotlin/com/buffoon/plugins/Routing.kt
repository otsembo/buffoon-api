package com.buffoon.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureRouting() {

    val create = "/user/create"
    val update = "/user/update/{uid}"
    val read = "/user/list"
    val delete = "/user/delete/{uid}"

    routing {

        post(create) {
            val user = call.receive<User>()
            var id = 0L
            transaction {
                id = UserEntity.new {
                    this.age = user.age
                    this.name = user.name
                    this.email = user.email
                }.id.value
                commit()
            }

            call.respond(
                DataResponse(status = "success", message = "You have created user: $id")
            )
        }

        get(read) {
            val userList = ArrayList<User>()
            transaction {
                UserEntity.all().forEach {
                    userList.add(it.getUser())
                }
                commit()
            }
            call.respond(userList)

        }

        put(update){
            val user = call.receive<User>()
            val id = call.parameters["uid"]
            transaction {
                UserEntity.findById(id?.toLong()!!)?.run {
                    this.age = user.age
                    this.name = user.name
                    this.email = user.email
                }
                commit()
            }

            call.respond(
                DataResponse(status = "success", message = "You have updated user: $id")
            )

        }

        delete(delete) {
            val id = call.parameters["uid"]
            transaction {
                id?.let {
                    UserEntity.findById(it.toLong())?.delete()
                }
                commit()
            }

            call.respond(
                DataResponse(status = "success", message = "You have deleted user: $id")
            )
        }

    }
}


@Serializable
data class DataResponse(
    val status: String,
    val message: String
)

