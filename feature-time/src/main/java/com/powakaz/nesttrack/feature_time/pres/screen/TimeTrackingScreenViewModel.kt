package com.powakaz.nesttrack.feature_time.pres.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_time.data.mapper.toDomain
import com.powakaz.nesttrack.feature_time.domain.model.activities.Activities
import com.powakaz.nesttrack.feature_time.domain.model.Concession
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance
import com.powakaz.nesttrack.feature_time.domain.model.TimeData
import com.powakaz.nesttrack.feature_time.domain.usecase.LoadTimeTrackingUseCase
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

private const val balanceCountdownStart = 0.0
@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class TimeTrackingScreenViewModel @Inject constructor(
    val loadTimeTrackingUseCase: LoadTimeTrackingUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(TimeTrackingUiState())

    val uiState: StateFlow<TimeTrackingUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {

            val result = loadTimeTrackingUseCase()

            when(result) {
                is NetworkResult.Error -> {
                    NetworkResult.Error(result.code, result.message)
                    Log.e("LOL", "error")
                }

                is NetworkResult.Exception -> {
                    NetworkResult.Exception(result.e)
                    Log.e("LOL", result.e.toString())
                }

                is NetworkResult.Success<TimeData> -> {

                    val timeBalance  = result.data.timeBalance.first().balanceHours
                    val activitiesList = result.data.activities
                    val concessionList = result.data.concessions.dataConcession


                    val currentBalanceState = if (timeBalance > balanceCountdownStart) {
                        BalanceState.OWE_ME
                    } else if (timeBalance < balanceCountdownStart) {
                        BalanceState.I_OWE
                    } else {
                        BalanceState.BALANCE
                    }

                    _uiState.update {
                        it.copy(
                            timeBalance = DateFormatter.formatDurationHours(timeBalance),
                            activitiesList = activitiesList.map { activities ->
                                activities.toUi()
                            },
                            currentBalanceState = currentBalanceState,
                            concessionList = concessionList.map { concession ->
                                concession.toUi()
                            }
                        )
                    }
                    Log.e("LOL", concessionList.toString())


                }
            }
        }
    }
}

data class TimeTrackingUiState(
    val timeBalance: String = "",
    val currentBalanceState: BalanceState = BalanceState.BALANCE,
    val activitiesList: List<ActivitiesUi> = emptyList(),
    val concessionList: List<ConcessionUi> = emptyList()
) {

}

enum class BalanceState { I_OWE, OWE_ME, BALANCE }