package com.powakaz.nesttrack.feature_profile.presentation.screen

import androidx.lifecycle.ViewModel
import com.powakaz.nesttrack.feature_profile.presentation.model.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel

class ProfileScreenViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    fun onEditNameClick() {
        _uiState.update {
            it.copy(isEditNameDialogVisible = true)
        }
    }

    fun closeEditNameDialog() {
        _uiState.update {
            it.copy(isEditNameDialogVisible = false)
        }
    }
}
data class ProfileUiState(
    val isEditNameDialogVisible: Boolean = false
)