package com.powakaz.nesttrack.feature_time.data.repository

import android.util.Log
import com.powakaz.core_common.repository.UserIdRepository
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.core_network.utils.safeApiCall
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.api.TimeTrackingApi
import com.powakaz.nesttrack.feature_time.data.mapper.toDomain
import com.powakaz.nesttrack.feature_time.domain.model.Activities
import com.powakaz.nesttrack.feature_time.domain.model.ConcessionList
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance
import com.powakaz.nesttrack.feature_time.domain.model.TimeData
import com.powakaz.nesttrack.feature_time.domain.repository.TimeTrackingRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TimeTrackingRepositoryImpl @Inject constructor(

    private val userIdRepository: UserIdRepository,
    val publicApi: TimeTrackingApi,
    val privateApi: TimeTrackingApi

) : TimeTrackingRepository {

    override suspend fun getTimeScreenData(): NetworkResult<TimeData> {

        val result = safeApiCall {
            coroutineScope {
                val userId = userIdRepository.getUserId().first()

                val timeBalanceDeferred = async {
                    publicApi.getBalanceConcession(userId).map {
                        it.toDomain()
                    }

                }

                val activitiesDeferred = async {
                    publicApi.getListActivities().map {
                        it.toDomain()
                    }
                }

                val concessionsDeferred = async {
                    publicApi.getListConcession(page = 1).toDomain()
                }


                TimeData(
                    timeBalance = timeBalanceDeferred.await(),
                    activities = activitiesDeferred.await(),
                    concessions = concessionsDeferred.await()
                )
            }
        }


        return result
    }
}