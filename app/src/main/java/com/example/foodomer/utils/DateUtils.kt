package com.example.foodomer.utils

import java.text.SimpleDateFormat
import java.util.*

val commonDateFormatter = SimpleDateFormat("hh:mm:ss a | EEE, dd MMMM, yyyy", Locale.US)
val dateOnlyFormatter = SimpleDateFormat("EEE, dd MMM, yyyy", Locale.US)
val timeOnlyFormatter = SimpleDateFormat("hh:mm a", Locale.US)
fun now(): Date = Date()
