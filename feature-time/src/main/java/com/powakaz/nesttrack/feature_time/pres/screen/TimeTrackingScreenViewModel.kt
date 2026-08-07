package com.powakaz.nesttrack.feature_time.pres.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powakaz.nesttrack.feature_time.domain.model.avatar.Avatar
import com.powakaz.nesttrack.feature_time.domain.model.avatar.UserProfile
import com.powakaz.nesttrack.feature_time.domain.usecase.LoadTimeTrackingUseCase
import com.powakaz.nesttrack.feature_time.domain.usecase.ObserveTimeDataUseCase
import com.powakaz.nesttrack.feature_time.pres.model.ActivitiesUi
import com.powakaz.nesttrack.feature_time.pres.model.ConcessionUi
import com.powakaz.nesttrack.feature_time.pres.utils.formatter.DateFormatter
import com.powakaz.nesttrack.feature_time.pres.utils.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
                            val myId = result.myId

                            val (avatarCreditor, avatarDebitor) = avatarsBalance(usersProfile, myId, timeBalance)




                           // Log.e("LOL", usersProfile.toString())
                            //Log.e("LOL", concessionList.toString())


                            val usersByName = usersProfile.associateBy { it.name }

                            _uiState.update {
                                it.copy(
                                    timeBalance = DateFormatter.formatDurationHours(timeBalance),
                                    activitiesList = activitiesList.map { activities ->
                                        activities.toUi()
                                    },
                                    currentBalanceState = mapBalance(timeBalance),
                                    concessionList = concessionList.map { concession ->

                                        val giver = usersByName[concession.giverName]
                                        val receiver = usersByName[concession.receiverName]

                                        concession.toUi(
                                            giverAvatar = giver!!.avatarUrl,
                                            receiverAvatar = receiver!!.avatarUrl
                                        )
                                    },
                                    avatarCreditor = avatarCreditor,
                                    avatarDebitor = avatarDebitor
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
fun avatarsBalance(usersProfile: List<UserProfile>, myId: Int, timeBalance: Double) : Pair<Avatar, Avatar> {

    val me = usersProfile.firstOrNull {
        it.id == myId
    }

    val other = usersProfile.firstOrNull {
        it.id != myId
    }

    val idOther = other?.id ?: 0

    val avatarMe = me?.avatarUrl ?: Avatar.Default(myId)
    val avatarOther = other?.avatarUrl ?: Avatar.Default(idOther)


    return if(timeBalance > balanceCountdownStart) {
        Pair(
            first = avatarMe,
            second = avatarOther
        )
    } else if (timeBalance < balanceCountdownStart) {
        Pair(
            first = avatarOther,
            second = avatarMe
        )
    } else {
        Pair(
            first = avatarOther,
            second = avatarMe
        )
    }
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

    val avatarCreditor: Avatar = Avatar.Default(0),
    val avatarDebitor: Avatar = Avatar.Default(0)

) {

}


enum class BalanceState { I_OWE, OWE_ME, BALANCE }
