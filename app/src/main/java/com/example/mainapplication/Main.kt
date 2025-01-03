package com.example.mainapplication
import kotlin.system.exitProcess

fun main() {
    play()
}

fun String.numberizer(): Float {
    var appendString = ""

    for (i in this) {
        if (i.isDigit()) {
            val result = i.toString()
            appendString += result
        }
    }

    return if (this.isNotEmpty()) {
        appendString.toFloat()
    } else {
        0.0f
    }
}


fun play() {
    println("შეიყვანე X:")
    val inputX = readln()

    println("შეიყვანე Y:")
    val inputY = readln()

    val resultX = inputX.numberizer()
    val resultY = inputY.numberizer()

    if (resultY == 0.0f) {
        println("0ზე გაყოფა არშეიძლება სცადეთ თავიდან!")
        play()
    } else {
        val resultZ = resultX / resultY
        println("X: $resultX, Y: $resultY")
        println("X და Y განაყოფი არის: $resultZ")
        tryAgain()
    }
}


fun tryAgain() {
    println("გსურთ პროგრამის ხელახლა დაწყება <Y/N>?")
    val answer = readln()

    when (answer.lowercase()) {
        "y" -> play()
        "n" -> exitProcess(0)
        else -> {
            println("შეიყვანე სწორი პასუხი Y/N")
            tryAgain()
        }
    }
}