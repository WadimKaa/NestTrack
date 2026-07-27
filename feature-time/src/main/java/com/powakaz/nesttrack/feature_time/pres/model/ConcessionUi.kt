package com.powakaz.nesttrack.feature_time.pres.model

import androidx.compose.ui.graphics.Color
import java.time.LocalDate


data class ConcessionUi(
    val id: Int,
    val giverName: String,
    val receiverName: String,
    val activityName: String,
    val activityIcon: String,
    val activityIconColor: Color,
    val activityBackgroundColor: Color,
    val durationHours: String,
    val description: String?,
    val createdAt: String,
    val activityDate: String
)