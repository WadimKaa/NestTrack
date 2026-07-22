package com.powakaz.nesttrack.feature_time.data.mapper

import android.util.Log
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.TimeBalanceResponseDto
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance
import java.time.LocalDate
import java.time.OffsetDateTime

fun TimeBalanceResponseDto.toDomain(): TimeBalance {
    return TimeBalance(
        userIdWith = userIdWith,
        userNameWith = userNameWith,
        balanceHours = balanceHours.toString() //-4.0
    )
}
