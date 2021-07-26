package com.megatest.myapplication.framework.presentation.util

import java.text.SimpleDateFormat
import java.util.*

private val dateFormat = SimpleDateFormat("MMM d yyyy", Locale.getDefault())
private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

fun Long.dateStr(): String {
    return dateFormat.format(this)
}

fun Long.timeStr(): String {
    return timeFormat.format(this)
}

fun Double.toFix2(): String {
    return String.format("%.2f", this)
}