package br.com.moviehub.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun String.toDate(): Calendar {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale("pt", "BR"))
    val date = formatter.parse(this)!!
    return Calendar.getInstance().apply { time = date }
}

fun Calendar.toDateString(): String {
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale("pt", "BR"))
    return formatter.format(this.time)
}
