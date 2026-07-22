package com.powakaz.nesttrack.feature_time.domain.usecase

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_time.domain.model.Activities
import com.powakaz.nesttrack.feature_time.domain.model.ConcessionList
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance
import com.powakaz.nesttrack.feature_time.domain.model.TimeData
import com.powakaz.nesttrack.feature_time.domain.repository.TimeTrackingRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class LoadTimeTrackingUseCase @Inject constructor(
    private val timeTrackingRepository: TimeTrackingRepository
){
    suspend operator fun invoke(): NetworkResult<TimeData>  {
        return timeTrackingRepository.getTimeScreenData()
    }
}