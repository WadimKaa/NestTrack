package com.powakaz.nesttrack.feature_time.domain.model

import kotlinx.serialization.SerialName

data class Concession(
    val id: Int,
    val giverName: String,
    val receiverName: String,
    val activityName: String,
    val activityIcon: String,
    val activityIconColor: String,
    val durationHours: String,
    val description: String?,
    val createdAt: String
)
