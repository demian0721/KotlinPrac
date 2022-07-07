package com.example.chapter2_kotlinbasics

fun main() {
    var myName = "Demian"
    val isEqual = 5 >= 5
    ifExam()
}

/*
* 변수 예제
* "Android Masterclass"
* 13.37F
* 3.14159265358979
* 25
 * 2020
* 18881234567
* true
* 'a'
*/

val stringExam: String = "Android Masterclass"
val floatExam: Float = 13.17F
val doubleExam: Double = 3.14159265358979
val shortExam: Byte = 25
val intExam: Short = 2020
val longExam: Long = 18881234567
val booleamExam: Boolean = true
val charExam: Char = 'a'

/*
* 비교연산자 중 포인트
* val a = 1
* println("${a++}") => 1
* 뒤에 ++가 붙으면 코드 실행이 끝난 시점에 값이 증가함.
* 즉, 변수에는 2가 할당되더라도 콘솔에 출력되는 시점에는 1이기 때문에 1이라고 출력됨.
* println("${++a}") => 3
* 변수 a 에 현재 2가 저장된 상태로 코드가 실행됨. ++가 앞에 붙으면 코드가 실행되기 전에 변수에 먼저 값을 증가시킴.
* 따라서 변수 a 에 할당된 값 3이 콘솔에도 동일하게 출력됨.
*/

//if문 예제
var age = 17
lateinit var message: String

fun ifExam() {
    message = if (age >= 21) "You may drink in the US"
    else if (age >= 18) "You may vote"
    else if (age >= 16) "You may drive"
    else "You are too young"
    println(message)
}