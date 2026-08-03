package com.powakaz.nesttrack.feature_time.data.repository

import android.R
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.powakaz.core_common.repository.UserIdRepository
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.core_network.utils.safeApiCall
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.api.TimeTrackingApi
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.activities.CreateActivitiesRequestDto
import com.powakaz.nesttrack.feature_time.data.mapper.findActivitiesColorToServer
import com.powakaz.nesttrack.feature_time.data.mapper.findActivitiesIconToServerName
import com.powakaz.nesttrack.feature_time.data.mapper.toDomain
import com.powakaz.nesttrack.feature_time.data.mapper.toDto
import com.powakaz.nesttrack.feature_time.domain.model.activities.Activities
import com.powakaz.nesttrack.feature_time.domain.model.ConcessionList
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance
import com.powakaz.nesttrack.feature_time.domain.model.TimeData
import com.powakaz.nesttrack.feature_time.domain.model.activities.create.CreateActivitiesRequest
import com.powakaz.nesttrack.feature_time.domain.model.activities.create.CreateActivitiesResponse
import com.powakaz.nesttrack.feature_time.domain.repository.TimeTrackingRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TimeTrackingRepositoryImpl @Inject constructor(

    @ApplicationContext
    private val context: Context,

    private val userIdRepository: UserIdRepository,
    val publicApi: TimeTrackingApi,
    val privateApi: TimeTrackingApi

) : TimeTrackingRepository {

    @RequiresApi(Build.VERSION_CODES.O)
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
                    publicApi.getListConcession().toDomain()//page = 1
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

    override suspend fun createNewActivities(newActivities: CreateActivitiesRequest): NetworkResult<CreateActivitiesResponse> {

        val result = safeApiCall {

            val dtoData = CreateActivitiesRequestDto(
                name = newActivities.name,
                iconName = context.findActivitiesIconToServerName(newActivities.iconName),
                iconColor = newActivities.iconColor.findActivitiesColorToServer()
            )


            val createActivities = privateApi.addNewActivities(
                activities = dtoData
            )

            createActivities.toDomain()
        }

        return result
    }
}