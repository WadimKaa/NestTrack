package com.powakaz.feature_auth.presentation



data class LoginUiState(val text : String = ""){

}


sealed interface LoginUiEvent{

}