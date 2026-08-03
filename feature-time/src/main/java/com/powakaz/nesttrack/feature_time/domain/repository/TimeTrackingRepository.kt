package com.powakaz.nesttrack.feature_time.domain.repository

import com.powakaz.core_common.repository.UserIdRepository
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_time.domain.model.activities.Activities
import com.powakaz.nesttrack.feature_time.domain.model.ConcessionList
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance
import com.powakaz.nesttrack.feature_time.domain.model.TimeData
import com.powakaz.nesttrack.feature_time.domain.model.activities.create.CreateActivitiesRequest
import com.powakaz.nesttrack.feature_time.domain.model.activities.create.CreateActivitiesResponse

interface TimeTrackingRepository {

    suspend fun getTimeScreenData() : NetworkResult<TimeData>

    suspend fun createNewActivities(newActivities: CreateActivitiesRequest) : NetworkResult<CreateActivitiesResponse>
}