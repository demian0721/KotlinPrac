package com.example.chapter3

data class User(val id: Long, val name: String)

fun main() {
    val user1 = User(1, "demian")
    val updatedUser = user1.copy(name = "Jae-in")
    println(user1)
    println(updatedUser)
    val (id, name) = updatedUser
    println(id)
    println(name)
}