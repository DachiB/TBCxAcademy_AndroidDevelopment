package com.example.mainapplication

data class Item(
    val title: String,
    val category: String,
    val image: Int = R.drawable.placeholder,
    val price: Double = 120.0,
    var favorite: Boolean = false
)
