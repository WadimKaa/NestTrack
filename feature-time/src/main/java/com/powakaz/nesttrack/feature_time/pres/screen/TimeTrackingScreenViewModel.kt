package com.powakaz.nesttrack.feature_time.pres.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_time.domain.model.TimeBalance
import com.powakaz.nesttrack.feature_time.domain.usecase.GetTimeBalanceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TimeTrackingScreenViewModel @Inject constructor(
    val getTimeBalanceUseCase: GetTimeBalanceUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(TimeTrackingUiState())

    val uiState: StateFlow<TimeTrackingUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = getTimeBalanceUseCase()
            when(result) {
                is NetworkResult.Error -> {
                    NetworkResult.Error(result.code, result.message)
                }

                is NetworkResult.Exception -> {
                    NetworkResult.Exception(result.e)
                }

                is NetworkResult.Success<List<TimeBalance>> -> {

                    val timeBalanceData = result.data.first().balanceHours

                    _uiState.update {
                        it.copy(
                            timeBalance = timeBalanceData
                        )
                    }
                }
            }
        }

    }
}

data class TimeTrackingUiState(
    val timeBalance: String = "",
) {

}