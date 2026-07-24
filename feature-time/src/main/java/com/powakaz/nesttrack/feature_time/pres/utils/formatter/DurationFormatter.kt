package com.powakaz.nesttrack.feature_time.pres.utils.formatter

import android.util.Log

object DateFormatter {
    fun formatDurationHours(hours: Double): String {
        val positiveHours = kotlin.math.abs(hours)

        val h = positiveHours.toInt()
        val m = ((positiveHours - h) * 100).toInt()

        return "%d ч %02d мин".format(h, m)
    }
}