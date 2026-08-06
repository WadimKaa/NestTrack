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
import com.powakaz.nesttrack.feature_time.domain.model.Concession
import com.powakaz.nesttrack.feature_time.domain.model.activities.Activities
import com.powakaz.nesttrack.feature_time.domain.model.ConcessionList
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance
import com.powakaz.nesttrack.feature_time.domain.model.TimeData
import com.powakaz.nesttrack.feature_time.domain.model.activities.create.CreateActivitiesRequest
import com.powakaz.nesttrack.feature_time.domain.model.activities.create.CreateActivitiesResponse
import com.powakaz.nesttrack.feature_time.domain.model.avatar.UserProfile
import com.powakaz.nesttrack.feature_time.domain.repository.TimeTrackingRepository
import com.powakaz.nesttrack.feature_time.pres.screen.TimeTrackingScreen
import com.powakaz.nesttrack.feature_time.pres.utils.mapper.toUi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import okhttp3.internal.http2.Http2Reader
import okhttp3.internal.wait
import java.util.logging.Handler
import javax.inject.Inject
import kotlin.collections.emptyList

class TimeTrackingRepositoryImpl @Inject constructor(

    @ApplicationContext
    private val context: Context,

    private val userIdRepository: UserIdRepository,
    val publicApi: TimeTrackingApi,
    val privateApi: TimeTrackingApi

) : TimeTrackingRepository {

    private val _timeData = MutableStateFlow<TimeData?>(null)
    val timeData: StateFlow<TimeData?> = _timeData.asStateFlow()


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun loadTimeScreenData() {

        _timeData.value =
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

                val usersListDiffered = async {
                    publicApi.getUsersProfile().map {
                        it.toDomain()
                    }
                }

                TimeData(
                    timeBalance = timeBalanceDeferred.await(),
                    activities = activitiesDeferred.await(),
                    concessions = concessionsDeferred.await(),
                    users = usersListDiffered.await(),
                    myId = userId
                )
            }

    }

    override fun observeTimeData(): StateFlow<TimeData?> {
        return timeData
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun createNewActivities(newActivities: CreateActivitiesRequest) {
        safeApiCall {

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

        loadListActivities()

    }

    suspend fun loadListActivities() {
        coroutineScope {
            val listActivities = publicApi.getListActivities().map {
                it.toDomain()
            }

            _timeData.update {
                it?.copy(
                    activities = listActivities
                )
            }
        }

    }
}