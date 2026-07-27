package com.powakaz.nesttrack.feature_time.pres.utils.formatter

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateFormatter {
    fun formatDurationHours(hours: Double): String {
        val positiveHours = kotlin.math.abs(hours)

        val h = positiveHours.toInt()
        val m = ((positiveHours - h) * 100).toInt()

        return "%d ч %02d мин".format(h, m)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(date: LocalDate): String {
        val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale("ru"))

        return date.format(outputFormatter)
    }
}