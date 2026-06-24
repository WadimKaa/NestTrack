package com.powakaz.feature_auth.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class LoginUiState(
    val token: String = "",
    val isTokenVisible: Boolean = true) {

}


sealed interface LoginUiEvent {
    data class TokenChanged(val token : String) : LoginUiEvent
}

class LoginViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()


    fun onEvent(event : LoginUiEvent){
        when(event){
            is LoginUiEvent.TokenChanged -> {
                _uiState.update {
                    it.copy(token = event.token)
                }
            }
        }

    }
}