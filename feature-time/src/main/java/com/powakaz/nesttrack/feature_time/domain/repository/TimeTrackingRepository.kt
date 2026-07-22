package com.powakaz.nesttrack.feature_time.domain.repository

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance

interface TimeTrackingRepository {

    suspend fun getTimeBalance(): NetworkResult<List<TimeBalance>>
}