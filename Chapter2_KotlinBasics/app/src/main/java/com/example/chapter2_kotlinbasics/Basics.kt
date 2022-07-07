package com.example.chapter2_kotlinbasics

fun main() {
    var myName = "Demian"
    val isEqual = 5 >= 5
//    ifExam()
//    whenExam()
//    whileExam()
//    forExam()
    forAndWhilePrac()
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

//when식 예제
var season = 3
var month = 3
var x: Any = 13.37

fun whenExam() {
    when (season) {
        1 -> println("Spring")
        2 -> println("Summer")
        3 -> {
            println("Fall")
            println("Autumn")
        }
        4 -> println("Winter")
        else -> println("Invalid Season")
    }
    when (month) {
        in 3..5 -> println("Spring")// in 키워드를 사용하여 x부터 y까지 범위에 포함된(x..y) 조건을 사용함.
        in 6..8 -> println("Summer")// 만약 역순으로 조건을 걸어야 할때는 ..이 아닌 down to를 사용한다.
        in 9..11 -> println {       // ex: in 10 down to 1 -> 요딴식
            ("Fall")
            ("Autumn")
        }
        12, 1, 2 -> println("Winter") // in 키워드로 포함시킬 수 없는 조건은 콤마를 사용할 수 있음.
        else -> println("Invalid Month")
    }
    when (x) {
        is Int -> println("$x is an Int") // is 키워드를 사용하여 타입에 따른 조건을 걸 수 있음.
        is Double -> println("$x is a Double")
        is String -> println("$x is a String")
        else -> println("$x is none of the above")
    }
}

// while 루프 예제
fun whileExam() {
    var x = 1
    while (x <= 10) {
        println("$x")
        x++
    }
    println("while loop is done")

    do {
        print("$x") // do 구문이 먼저 실행됨으로 11이 출력됨
        x++
    } while (x <= 10) // 해당 while 루프문의 조건이 do 구문보다 뒤에 실행됨.
    println("\ndo while loop is done") // 결과적으로 11이라고 한번은 실행됨.
}

// for 루프 예제
fun forExam() {
    for (num in 1..10) { // in .. 은 1이상 10이하 즉, 1에서 10까지를 포함한 범위
        print("$num")
    }
    for (i in 1 until 10) { // until은 1이상 10미만 즉, 1에서 10이전까지를 포함한 범위
        print("\n$i")
    }
    for (i in 10 downTo 1 step 2) { // downTo는 10이하 1이상 즉, 10에서 1까지를 포함한 범
        print("\n$i")                    // step은 반복하는 간격 (10 8 6 .. 2)
    }

}

fun forAndWhilePrac() {
    var humidity = "humid"
    var humidityLevel = 80
    for (h in 0..10000) {
        if (h > 9000) print("IT'S 9000 OVER!!!")
    }
    while (humidity == "humid") {
        humidityLevel -= 5
        println("humidity decreased")
        if (humidityLevel <= 60) {
            humidity = "comfy"
            println("it's comfy now")
        }
    }
}