package com.powakaz.nesttrack.feature_time.pres

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class TimeTrackingScreenViewModel @Inject constructor(
    //usecase
) : ViewModel() {
    private val _uiState = MutableStateFlow(TimeTrackingUiState())

    val uiState: StateFlow<TimeTrackingUiState> = _uiState.asStateFlow()

    init {

    }
}

data class TimeTrackingUiState(
    val lol: String = ""
) {

}