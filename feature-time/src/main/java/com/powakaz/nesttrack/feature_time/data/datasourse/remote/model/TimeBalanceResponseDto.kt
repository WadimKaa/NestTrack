package com.powakaz.nesttrack.feature_time.data.datasourse.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TimeBalanceResponseDto(
    @SerialName("with_user_id")
    val userIdWith: Int,
    @SerialName("with_user_name")
    val userNameWith: String,
    @SerialName("balance_hours")
    val balanceHours: Double
) {
}