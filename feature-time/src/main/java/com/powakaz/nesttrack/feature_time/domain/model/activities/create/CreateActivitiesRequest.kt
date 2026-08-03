package com.powakaz.nesttrack.feature_time.domain.model.activities.create

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.SerialName

data class CreateActivitiesRequest(
    val name: String,
    val iconName: Int,
    val iconColor: Color
)
