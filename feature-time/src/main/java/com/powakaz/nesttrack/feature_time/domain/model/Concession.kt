package com.powakaz.nesttrack.feature_time.domain.model

import kotlinx.serialization.SerialName
import java.time.LocalDate

data class Concession(
    val id: Int,
    val giverName: String,
    val receiverName: String,
    val activityName: String,
    val activityIcon: String,
    val activityIconColor: String,
    val durationHours: Double,
    val description: String?,
    val createdAt: String,
    val activityDate: LocalDate
)
