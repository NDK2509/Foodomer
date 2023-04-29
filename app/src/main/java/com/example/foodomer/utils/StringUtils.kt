package com.example.foodomer.utils

fun String.capitalize(): String {
    return this
        .split("\\s+".toRegex())
        .joinToString(" ") {
            it.lowercase().replaceFirstChar(Char::titlecase)
        }
}