package com.powakaz.nesttrack.feature_time.domain.usecase

import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance
import com.powakaz.nesttrack.feature_time.domain.repository.TimeTrackingRepository
import javax.inject.Inject

class GetTimeBalanceUseCase @Inject constructor(
    private val timeTrackingRepository: TimeTrackingRepository
){
    suspend operator fun invoke(): NetworkResult<List<TimeBalance>> = timeTrackingRepository.getTimeBalance()
}