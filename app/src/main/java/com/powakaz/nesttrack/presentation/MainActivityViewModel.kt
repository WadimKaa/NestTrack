package com.powakaz.nesttrack.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powakaz.core_common.manager.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class MainActivityUiState(
    val isLoggedIn: Boolean? = null
)

@HiltViewModel
class MainActivityViewModel @Inject constructor(sessionManager: SessionManager, ) : ViewModel() {

    private val _uiState = MutableStateFlow(MainActivityUiState())
    val uiState: StateFlow<MainActivityUiState> = _uiState.asStateFlow()

    init {
        sessionManager.isLoggedIn()
            .onEach { loggedIn ->
                _uiState.update {
                    it.copy(isLoggedIn = loggedIn)
                }
            }
            .launchIn(viewModelScope)
    }
}