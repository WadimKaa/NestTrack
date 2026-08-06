package com.powakaz.nesttrack.feature_time.pres.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.util.CoilUtils
import coil3.util.CoilUtils.result
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.core_network.model.NetworkResult.*
import com.powakaz.nesttrack.feature_time.data.mapper.toDomain
import com.powakaz.nesttrack.feature_time.domain.model.activities.Activities
import com.powakaz.nesttrack.feature_time.domain.model.Concession
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance
import com.powakaz.nesttrack.feature_time.domain.model.TimeData
import com.powakaz.nesttrack.feature_time.domain.model.avatar.Avatar
import com.powakaz.nesttrack.feature_time.domain.model.avatar.UserProfile
import com.powakaz.nesttrack.feature_time.domain.usecase.LoadTimeTrackingUseCase
import com.powakaz.nesttrack.feature_time.domain.usecase.ObserveTimeDataUseCase
import com.powakaz.nesttrack.feature_time.pres.model.ActivitiesUi
import com.powakaz.nesttrack.feature_time.pres.model.ConcessionUi
import com.powakaz.nesttrack.feature_time.pres.utils.formatter.DateFormatter
import com.powakaz.nesttrack.feature_time.pres.utils.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.first

private const val balanceCountdownStart = 0.0

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class TimeTrackingScreenViewModel @Inject constructor(
    val loadTimeTrackingUseCase: LoadTimeTrackingUseCase,
    val observeTimeDataUseCase: ObserveTimeDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TimeTrackingUiState())
    val uiState: StateFlow<TimeTrackingUiState> = _uiState.asStateFlow()

    init {
        loadScreen()

    }

    fun loadScreen() {

        viewModelScope.launch {
            launch {
                observeTimeDataUseCase().collect { result ->

                    when (result) {

                        null -> {}

                        else -> {
                            val timeBalance = result.timeBalance.first().balanceHours
                            val activitiesList = result.activities
                            val concessionList = result.concessions.dataConcession
                            val usersProfile = result.users
                            val creditorUserId = result.timeBalance.first().userIdWith

                            val (avatarCreditor, avatarDebitor) = avatarsBalance(usersProfile, creditorUserId)


                            _uiState.update {
                                it.copy(
                                    timeBalance = DateFormatter.formatDurationHours(timeBalance),
                                    activitiesList = activitiesList.map { activities ->
                                        activities.toUi()
                                    },
                                    currentBalanceState = mapBalance(timeBalance), //.currentBalanceState
                                    concessionList = concessionList.map { concession ->
                                        concession.toUi()
                                    },
                                    avatar1 = avatarCreditor,
                                    avatar2 = avatarDebitor
                                )
                            }
                        }
                    }
                }
            }

            loadTimeTrackingUseCase()
        }
    }
}
fun avatarsBalance(usersProfile: List<UserProfile>, creditorUserId: Int) : Pair<Avatar, Avatar>{
    var avatarMap: Map<Int, Avatar?> = emptyMap()

    avatarMap = usersProfile.associate { profile ->
        profile.id to profile.avatarUrl
    }

    val avatarCreditor = avatarMap[creditorUserId] ?: Avatar.Default
    val debtorUserId = avatarMap.keys.first{it != creditorUserId}
    val avatarDebitor = avatarMap[debtorUserId] ?: Avatar.Default

    return Pair(avatarCreditor, avatarDebitor)
}

fun mapBalance(timeBalance: Double): BalanceState {
    val currentBalanceState = if (timeBalance > balanceCountdownStart) {
        BalanceState.OWE_ME
    } else if (timeBalance < balanceCountdownStart) {
        BalanceState.I_OWE
    } else {
        BalanceState.BALANCE
    }
    return currentBalanceState
}


data class TimeTrackingUiState(
    val timeBalance: String = "",
    val currentBalanceState: BalanceState = BalanceState.BALANCE,
    val activitiesList: List<ActivitiesUi> = emptyList(),
    val concessionList: List<ConcessionUi> = emptyList(),
    val usersProfile: List<UserProfile> = emptyList(),

    val avatar1: Avatar = Avatar.Default,
    val avatar2: Avatar = Avatar.Default

) {

}


enum class BalanceState { I_OWE, OWE_ME, BALANCE }
