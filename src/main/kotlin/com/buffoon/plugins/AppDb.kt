package com.buffoon.plugins

import io.ktor.server.application.*
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


fun configureDB(){

    Database.connect("jdbc:sqlite:db/buffoon.sqlite", driver = "org.sqlite.JDBC")
    transaction {
        SchemaUtils.create(
            UserTable
        )
        commit()
    }

}

@Serializable
data class User(
    val id: Long = 0L,
    val name: String?,
    val age: Int,
    val email: String?
)

object UserTable : LongIdTable("users"){
    val name = text("name").nullable()
    val age = integer("age").default(defaultValue = 0)
    val email = text("email").nullable()
}

class UserEntity(id: EntityID<Long>) : LongEntity(id){
    companion object : EntityClass<Long, UserEntity>(UserTable)
    var name by UserTable.name
    var age by UserTable.age
    var email by UserTable.email

    fun getUser() : User{
        return User(
            id = id.value,
            name = name,
            age = age,
            email = email
        )
    }

}