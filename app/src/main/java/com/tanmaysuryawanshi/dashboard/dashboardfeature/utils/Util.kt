package com.tanmaysuryawanshi.dashboard.dashboardfeature.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun convertDateFormat(input: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    return try {
        val date = inputFormat.parse(input)
        outputFormat.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        input
    }
}