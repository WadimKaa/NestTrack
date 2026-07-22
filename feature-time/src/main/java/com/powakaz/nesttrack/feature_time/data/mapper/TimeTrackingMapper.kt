package com.powakaz.nesttrack.feature_time.data.mapper

import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.ActivitiesResponseDto
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.ConcessionDto
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.ConcessionListResponseDto
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.TimeBalanceResponseDto
import com.powakaz.nesttrack.feature_time.domain.model.Activities
import com.powakaz.nesttrack.feature_time.domain.model.Concession
import com.powakaz.nesttrack.feature_time.domain.model.ConcessionList
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance

fun TimeBalanceResponseDto.toDomain(): TimeBalance {
    return TimeBalance(
        userIdWith = userIdWith,
        userNameWith = userNameWith,
        balanceHours = balanceHours.toString() //-4.0
    )
}

fun ActivitiesResponseDto.toDomain(): Activities {
    return Activities(
        id = id,
        name = name,
        iconName = iconName,
        iconColor = iconColor
    )
}

fun ConcessionListResponseDto.toDomain(): ConcessionList {
    return ConcessionList(
        page = page,
        totalRecords = totalRecords,
        hasMorePage = hasMorePage,
        dataConcession = dataConcession
    )
}
fun ConcessionDto.toDomain(): Concession {
    return Concession(
        id = id,
        giverName = giverName,
        receiverName = receiverName,
        activityName = activityName,
        activityIcon = activityIcon,
        activityIconColor = activityIconColor,
        durationHours = durationHours.toString(),
        description = description,
        createdAt = createdAt
    )
}
