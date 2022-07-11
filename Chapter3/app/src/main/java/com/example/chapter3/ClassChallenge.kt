package com.example.chapter3

fun main() {
    var myPhone = MobilePhone("Android", "Galaxy S22 Ultra", "Samsung", 15)
    myPhone.battery = 20
    myPhone.chargeBattery(5)
    myPhone.chargeBattery(10)
    myPhone.chargeBattery(15)
}

class MobilePhone(onName: String, model: String, brand: String) {

    var battery: Int = 0

    init {
        this.battery = battery
        println("The phone $model from $brand uses $onName as its Operating System")
    }

    constructor(onName: String, model: String, brand: String, battery: Int) : this(
        onName,
        model,
        brand
    ) {
        val battery: Int = battery
        println("The phone $model from $brand uses $onName as its Operating System, (Battery:${battery}%)")
    }

    fun chargeBattery(charged: Int) {
        println("prevBatter: ${battery}% charged: ${charged}% currentBattery: ${battery + charged}%")
        battery += charged
    }
}