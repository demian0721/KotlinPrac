package com.example.chapter3

import java.lang.IllegalArgumentException

fun main() {
//    val demian = Person("Demian", "Joo", 35)
//    demian.hobby = "Playing PC Online Game"
//    demian.stateHobby()
    var myCar = Car()
    println(myCar.myBrand)
    myCar.maxSpeed = 280
    println(myCar.maxSpeed)
}


// Class 기본 예제 Constructor(생성자) 구문은 생략이 가능하다
class Person(firstName: String = "John", lastName: String = "Doe") {
    // Member Variables - Properties
    var firstName = firstName
    var age: Int? = null
    var hobby: String = "Watch Netflix"

    // init Block: 클래스가 호출되면 자동으로 실행되는 구문
    init {
        var firstName = firstName
        println(
            "Initialized a new Person object with" +
                    "firstName = $firstName and lastName = $lastName"
        )
    }

    // Member secondary Constructor (보조 생성자)
    constructor(firstName: String, lastName: String, age: Int) : this(firstName, lastName) {
        this.age = age
        println(
            "Initialized a new Person object with" +
                    "firstName = $firstName and lastName = $lastName and age $age"
        )
    }

    // Member functions = Methods
    fun stateHobby() {
        println("${firstName}'s hobby is $hobby")
    }
}

// Getter & Setter 예제
class Car() {
    lateinit var owner: String
    val myBrand: String = "BMW"
        // Custom Getter
        get() {
            return field.toLowerCase()
        }
    var maxSpeed: Int = 250
        set(value) {
            field =
                if (value > 0) value else throw IllegalArgumentException("Maxspeed is cannot be less than 0")
        }
    var myModel: String = "M5"
        private set

    init {
        this.owner = "Frank"
    }
}