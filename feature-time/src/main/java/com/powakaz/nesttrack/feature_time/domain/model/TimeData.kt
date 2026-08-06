package com.powakaz.nesttrack.feature_time.domain.model

import com.powakaz.nesttrack.feature_time.domain.model.activities.Activities
import com.powakaz.nesttrack.feature_time.domain.model.avatar.UserProfile

data class TimeData(
    val timeBalance: List<TimeBalance>,
    val activities: List<Activities>,
    val concessions: ConcessionList,
    val users: List<UserProfile>,
    val myId: Int
)