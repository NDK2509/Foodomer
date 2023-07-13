package com.example.foodomer.utils

import java.text.SimpleDateFormat
import java.util.*

val commonDateFormatter = SimpleDateFormat("HH:mm:ss a | EEE, dd MMMM, yyyy", Locale.US)
fun now(): Date = Date()
