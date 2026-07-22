package com.powakaz.nesttrack.feature_time.pres.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_time.domain.model.Activities
import com.powakaz.nesttrack.feature_time.domain.model.Concession
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance
import com.powakaz.nesttrack.feature_time.domain.model.TimeData
import com.powakaz.nesttrack.feature_time.domain.usecase.LoadTimeTrackingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


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

                    _uiState.update {
                        it.copy(
                            timeBalance = timeBalance,
                            activitiesList = activitiesList
                        )
                    }

                }
            }
        }
    }
}

data class TimeTrackingUiState(
    val timeBalance: String = "",
    val activitiesList: List<Activities> = emptyList(),
    val concessionList: List<Concession> = emptyList()
) {

}