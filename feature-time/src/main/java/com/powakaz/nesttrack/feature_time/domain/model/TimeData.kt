package com.powakaz.nesttrack.feature_time.domain.model

import com.powakaz.nesttrack.feature_time.domain.model.activities.Activities

data class TimeData(
    val timeBalance: List<TimeBalance>,
    val activities: List<Activities>,
    val concessions: ConcessionList
)