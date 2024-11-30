package com.example.mainapplication

fun main() {
    println(usg(18, 36))
    println(hasDollar("Guc${'$'}ciMane"))
    println(palindromeCheck("madam"))
    println(evenSum())
}

fun usg(first: Int, second: Int): Int {
    var usg = 0
    val min = if (first < second) first else second
    val max = if (first > second) first else second
    println("$min , $max")
    for (i in min downTo 1) {
        if (max % i == 0 && min % i == 0) {
            usg = i
            break
        }
    }
    return usg
}

fun evenSum(): Int {
    var sum = 0
    for (i in 1..100) {
        if (i % 2 == 0) {
            sum += i
        }
    }
    return sum
}

fun hasDollar(str: String): Boolean {
    var hasDollar = false
    for (char in str) {
        if (char == '$') {
            hasDollar = true
            break
        }
    }
    return hasDollar
}

fun palindromeCheck (str: String): Boolean {
    val isPalindrome = str.lowercase() == str.lowercase().reversed()
    return isPalindrome
}

//fun palindromeCheck2 (str: String) {
//    val result = ceil(str.length.toFloat()/2).toInt()
//    for (i in str.length - 1 downTo result) {
//        println(str[i])
//        for (j in 0 until str.length / 2) {
//            println(str[j])
//        }
//    }
//
//}