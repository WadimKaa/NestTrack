package com.powakaz.nesttrack.feature_time.domain.model

import kotlinx.serialization.SerialName

data class TimeBalance(
    val userIdWith: Int,
    val userNameWith: String,
    val balanceHours: String
)
