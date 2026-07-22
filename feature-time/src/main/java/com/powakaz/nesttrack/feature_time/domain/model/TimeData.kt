package com.powakaz.nesttrack.feature_time.domain.model

data class TimeData(
    val timeBalance: List<TimeBalance>,
    val activities: List<Activities>,
    val concessions: ConcessionList
)