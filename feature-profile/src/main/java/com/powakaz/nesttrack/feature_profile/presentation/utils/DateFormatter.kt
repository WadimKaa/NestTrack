package com.powakaz.nesttrack.feature_profile.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(dateMillis: Long): String {
    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale("ru"))
    return formatter.format(Date(dateMillis))
}