package com.example.foodomer.utils

fun Int.thousandGroupByDot(): String {
    return String.format("%,d", this).replace(",", ".")
}