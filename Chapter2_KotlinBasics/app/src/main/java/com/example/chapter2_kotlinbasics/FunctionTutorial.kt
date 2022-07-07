package com.example.chapter2_kotlinbasics

fun main() {
    // Argument (3, 5)
    val result = avg(3.0, 4.0)
    println("result is $result")
}

// Parameter (a, b)
fun addUp(a: Int, b: Int): Int {
    return a + b
}

// Tutorial
fun avg(a: Double, b: Double): Double {
    return (a + b) / 2
}