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

    fun showEditNameDialog() {
        _uiState.update {
            it.copy(
                isEditNameDialogVisible = true,
                //editedName = it.currentName
            )
        }
    }

    fun closeEditNameDialog() {
        _uiState.update {
            it.copy(isEditNameDialogVisible = false)
        }
    }


    fun onNameChanged(name: String) {
        _uiState.update {
            it.copy(editedName = name)
        }
    }

    fun saveName() {
        _uiState.update {
            it.copy(
                currentName = it.editedName,
                editedName = "",
                isEditNameDialogVisible = false
            )
        }
    }
}

data class ProfileUiState(
    val currentName: String = "",
    val editedName: String = "",
    val isEditNameDialogVisible: Boolean = false
)
{
    val isSaveEnabled: Boolean
        get() = editedName.isNotBlank()&&
                editedName != currentName
}