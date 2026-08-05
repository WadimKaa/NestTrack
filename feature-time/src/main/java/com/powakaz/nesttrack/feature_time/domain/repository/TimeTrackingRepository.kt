package com.powakaz.nesttrack.feature_time.domain.repository

import com.powakaz.core_common.repository.UserIdRepository
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_time.domain.model.activities.Activities
import com.powakaz.nesttrack.feature_time.domain.model.ConcessionList
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance
import com.powakaz.nesttrack.feature_time.domain.model.TimeData
import com.powakaz.nesttrack.feature_time.domain.model.activities.create.CreateActivitiesRequest
import com.powakaz.nesttrack.feature_time.domain.model.activities.create.CreateActivitiesResponse
import com.powakaz.nesttrack.feature_time.domain.model.avatar.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface TimeTrackingRepository {
    suspend fun loadAvatars() : NetworkResult<List<UserProfile>>

    suspend fun loadTimeScreenData()

    fun observeTimeData() : StateFlow<TimeData?>

    suspend fun createNewActivities(newActivities: CreateActivitiesRequest)
}