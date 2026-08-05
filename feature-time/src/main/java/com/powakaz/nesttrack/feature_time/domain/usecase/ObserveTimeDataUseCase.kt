package com.powakaz.nesttrack.feature_time.domain.usecase

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_time.domain.model.TimeData
import com.powakaz.nesttrack.feature_time.domain.repository.TimeTrackingRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ObserveTimeDataUseCase @Inject constructor(
    private val timeTrackingRepository: TimeTrackingRepository
) {
    operator fun invoke(): StateFlow<TimeData?> = timeTrackingRepository.observeTimeData()
}