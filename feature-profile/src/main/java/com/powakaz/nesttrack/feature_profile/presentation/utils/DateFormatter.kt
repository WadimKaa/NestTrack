package com.powakaz.nesttrack.feature_profile.presentation.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun formatDate(dateMillis: Long): String {
    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale("ru"))
    return formatter.format(Date(dateMillis))
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateMountToText(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
    return date.format(formatter)
}