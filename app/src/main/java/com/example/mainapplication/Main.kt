package com.example.mainapplication


fun main() {
    println("უსგ:${usg(72, 12)}")
    println("უსჯ:${usj(12, 18)}")
    println("აქვს $?: ${hasDollar("Guc${'$'}ciMane$")}")
    println("ლუწთა ჯამი: ${evenSum()}")
    println("შებრუნებული: ${reverser(12340)}")
    println("პოლინდრომი: ${palindromeCheck("madam")}")
}

fun usg(first: Int, second: Int): Int {
    var usg = 0
    val min = if (first < second) first else second
    val max = if (first > second) first else second
    for (i in min downTo 1) {
        if (max % i == 0 && min % i == 0) {
            usg = i
            break
        }
    }
    return usg
}

fun usj(first: Int, second: Int): Int {
    var usj = 0
    val min = if (first < second) first else second
    val max = if (first > second) first else second
    for (i in 1..min) {
        val result = min * i
        if (result % max == 0) {
            usj = result
            break
        }
    }
    return usj
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

fun evenSum(): Int {
    var sum = 0
    for (i in 1..100) {
        if (i % 2 == 0) {
            sum += i
        }
    }
    return sum
}

fun reverser(first: Int): Int {
    val reversed = first.toString().reversed().toInt()
    return reversed
}

fun palindromeCheck (str: String): Boolean {
    val isPalindrome = str.lowercase() == str.lowercase().reversed()
    return isPalindrome
}

//პოლინდრომის manual check
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