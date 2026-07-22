package com.powakaz.nesttrack.feature_time.domain.repository

import com.powakaz.core_common.repository.UserIdRepository
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_time.domain.model.Activities
import com.powakaz.nesttrack.feature_time.domain.model.ConcessionList
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance
import com.powakaz.nesttrack.feature_time.domain.model.TimeData

interface TimeTrackingRepository {

    suspend fun getTimeScreenData() : NetworkResult<TimeData>
}