package com.powakaz.nesttrack.feature_profile.presentation.screen

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.powakaz.nesttrack.feature_profile.presentation.model.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel

class ProfileScreenViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()


    ///////////////////dialog avatar/////////////////////////////

    fun showEditAvatarDialog() {
        _uiState.update {
            it.copy(
                isEditAvatarDialogVisible = true
            )
        }
    }

    fun closeEditAvatarDialog() {
        _uiState.update {
            it.copy(isEditAvatarDialogVisible = false)
        }
    }

    fun onAvatarSelected(uri: Uri) {
        _uiState.update {
            it.copy(
                selectedAvatar = uri
            )
        }
    }

    ////////////////////dialog Birth/////////////////////////////
    fun showEditBirthDialog() {
        _uiState.update {
            it.copy(
                isEditBirthDialogVisible = true
            )
        }
    }

    fun closeEditBirthDialog() {
        _uiState.update {
            it.copy(isEditBirthDialogVisible = false)
        }
    }

    fun saveBirth() {
        _uiState.update {
            it.copy(
                isEditBirthDialogVisible = false,
                editedBirthDate = null,
                //

            )
        }
    }

    fun showDatePicker() {
        _uiState.update {
            it.copy(isDatePickerVisible = true)
        }
    }

    fun closeDatePicker() {
        _uiState.update {
            it.copy(isDatePickerVisible = false)
        }
    }

    fun onBirthDateSelected(dateMillis: Long) {
        _uiState.update {
            it.copy(
                editedBirthDate = dateMillis,
                isDatePickerVisible = false,

                )
        }
    }


    ///////////////////////////dialog name///////////////////////////////////

    fun showEditNameDialog() {
        _uiState.update {
            it.copy(
                isEditNameDialogVisible = true,
                editedName = it.profile?.name.orEmpty()
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
                editedName = "",
                isEditNameDialogVisible = false
            )
        }
    }
}

data class ProfileUiState(
    val profile: UserProfile? = null,

    val editedName: String = "",
    val isEditNameDialogVisible: Boolean = false,

    val isEditBirthDialogVisible: Boolean = false,
    val isDatePickerVisible: Boolean = false,
    val editedBirthDate: Long? = null,

    val isEditAvatarDialogVisible: Boolean = false,
    val selectedAvatar: Uri? = null,
) {
    val isSaveEnabled: Boolean
        get() = editedName.isNotBlank() &&
                editedName != profile?.name
}

data class UserProfile(
    val id: Int,
    val name: String,
    val birthDate: Long?,
    val avatarUrl: String? = null
)