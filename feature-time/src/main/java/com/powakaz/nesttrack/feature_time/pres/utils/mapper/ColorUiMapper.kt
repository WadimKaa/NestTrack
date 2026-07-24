package com.powakaz.nesttrack.feature_time.pres.utils.mapper

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt


fun String.findActivitiesColorToUi(
    default: Color = Color.Gray,
    alpha: Float = 1f
): Color {
    return try {
        Color(this.toColorInt())
            .copy(alpha = alpha)
    } catch (_: IllegalArgumentException) {
        default.copy(alpha = alpha)
    }
}

