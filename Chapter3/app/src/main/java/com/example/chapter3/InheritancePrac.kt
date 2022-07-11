package com.example.chapter3

//상속 개념 예제

fun main() {
    var BMWX6 = Cars("X6", "BMW")
    var KIAEV6 = ElectricCar("EV6", "KIA", 85.0)
    KIAEV6.extendRange(200.0)
    BMWX6.drive(200.0)
    KIAEV6.drive(200.0)
}

// Super Class, Parent Class, Base Class
open class Cars(
    val name: String,
    val brand: String
) {
    open var range: Double = 0.0
    fun extendRange(amount: Double) {
        if (amount > 0)
            range += amount
    }

    open fun drive(distance: Double) {
        println("Drove for $distance KM")
    }
}

// Sub Class of Car, Derived Class of Car,
class ElectricCar(name: String, brand: String, batteryLife: Double) : Cars(name, brand) {
    override var range = batteryLife * 5
    override fun drive(distance: Double) {
        println("Drove for $distance KM on Electricity")
    }
}