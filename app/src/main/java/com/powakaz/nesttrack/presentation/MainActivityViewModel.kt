package com.powakaz.nesttrack.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powakaz.core_common.manager.SessionManager
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.feature_finance.domain.usecase.GetFinancialDashboardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class LoginState { INITIAL, LOGINED, NOT_LOGINED }

data class MainActivityUiState(
    val loginState: LoginState = LoginState.INITIAL
)

@HiltViewModel
class MainActivityViewModel @Inject constructor(sessionManager: SessionManager) : ViewModel() {

    private val _uiState = MutableStateFlow(MainActivityUiState())
    val uiState: StateFlow<MainActivityUiState> = _uiState.asStateFlow()

    init {
        sessionManager.isLoggedIn()
            .onEach { loggedIn ->
                when (loggedIn) {
                    true -> _uiState.update {
                        it.copy(loginState = LoginState.LOGINED)
                    }

                    false -> _uiState.update {
                        it.copy(loginState = LoginState.NOT_LOGINED)
                    }
                }
            }
            .launchIn(viewModelScope)


    }
}