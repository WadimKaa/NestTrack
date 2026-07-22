package com.powakaz.nesttrack.feature_time.data.repository

import android.util.Log
import com.powakaz.core_common.repository.UserIdRepository
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.core_network.utils.safeApiCall
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.api.TimeTrackingApi
import com.powakaz.nesttrack.feature_time.data.mapper.toDomain
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance
import com.powakaz.nesttrack.feature_time.domain.repository.TimeTrackingRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TimeTrackingRepositoryImpl @Inject constructor(

    private val userIdRepository: UserIdRepository,
    val publicApi: TimeTrackingApi,
    val privateApi: TimeTrackingApi

) : TimeTrackingRepository {
    override suspend fun getTimeBalance(): NetworkResult<List<TimeBalance>> {

        val result = safeApiCall {
            val userId = userIdRepository.getUserId().first()
            val timeBalanceList = publicApi.getBalanceConcession(userId)

            timeBalanceList.map {
                it.toDomain()

            }

        }
        return result
    }
}