package com.powakaz.nesttrack.feature_time.domain.usecase

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_time.domain.model.activities.create.CreateActivitiesRequest
import com.powakaz.nesttrack.feature_time.domain.model.activities.create.CreateActivitiesResponse
import com.powakaz.nesttrack.feature_time.domain.repository.TimeTrackingRepository
import javax.inject.Inject

class CreateNewActivitiesUseCase @Inject constructor(
    private val repository: TimeTrackingRepository
){
    suspend operator fun invoke(newActivities: CreateActivitiesRequest)  {
        return repository.createNewActivities(newActivities)
    }
}