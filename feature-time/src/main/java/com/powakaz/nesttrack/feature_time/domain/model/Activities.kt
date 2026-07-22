package com.powakaz.nesttrack.feature_time.domain.model

import kotlinx.serialization.SerialName

data class Activities(
    val id: Int,
    val name: String,
    val iconName: String,
    val iconColor: String
)