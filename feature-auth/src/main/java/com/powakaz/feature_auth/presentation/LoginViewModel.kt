package com.powakaz.feature_auth.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.feature_auth.domain.usecase.CheckTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class LoginState { NORMAL, TOKEN_CHECK, TOKEN_RIGHT, TOKEN_WRONG, NETWORK_ERROR, ERROR }

data class LoginUiState(
    val token: String = "",
    val isTokenVisible: Boolean = true,
    val currentState: LoginState = LoginState.NORMAL
) {
    val isButtonLoginEnabled = token.length > 5
    val isNeedShowError =
        currentState == LoginState.TOKEN_WRONG || currentState == LoginState.NETWORK_ERROR || currentState == LoginState.ERROR
    val isNeedShowErrorGear =
         currentState == LoginState.NETWORK_ERROR || currentState == LoginState.ERROR
}


sealed interface LoginUiEvent {
    data class TokenChanged(val token: String) : LoginUiEvent

    object ClickLoginButon : LoginUiEvent
    object ChangeTokenVisibility : LoginUiEvent
}


@HiltViewModel
class LoginViewModel @Inject constructor(val checkTokenUseCase: CheckTokenUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()


    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.TokenChanged -> {
                _uiState.update {
                    it.copy(token = event.token)
                }
            }

            LoginUiEvent.ChangeTokenVisibility -> {
                _uiState.update {
                    it.copy(isTokenVisible = !it.isTokenVisible)
                }
            }

            //TODO обработать ответы

            LoginUiEvent.ClickLoginButon -> {
                viewModelScope.launch {
                   val response = checkTokenUseCase(uiState.value.token)

                    when(response){
                        is NetworkResult.Success -> {
                            Log.e("LOL", response.data.name)
                        }

                        is NetworkResult.Error -> {
                            Log.e("LOL", response.message.toString())
                        }
                        is NetworkResult.Exception -> {
                            Log.e("LOL", response.e.message.toString())
                        }
                    }
                }
            }
        }

    }
}