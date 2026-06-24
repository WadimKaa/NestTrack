package com.powakaz.feature_auth.presentation


data class LoginUiState(
    val text: String = "",
    val isTokenVisible: Boolean = true) {

}


sealed interface LoginUiEvent {

}