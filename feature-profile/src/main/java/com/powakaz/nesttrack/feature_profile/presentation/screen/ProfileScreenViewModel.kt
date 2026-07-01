package com.powakaz.nesttrack.feature_profile.presentation.screen

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.powakaz.nesttrack.feature_profile.presentation.model.ProfileUiState
import com.powakaz.nesttrack.feature_profile.presentation.state.ProfileDialog
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


    fun dismissDialog() {
        _uiState.update { state ->
            when (val dialog = state.activeDialog) {

                is ProfileDialog.DatePicker -> {
                    state.copy(activeDialog = dialog.returnTo)
                }

                else -> {
                    state.copy(activeDialog = ProfileDialog.None)
                }
            }
        }
    }


    ///////////////////dialog avatar/////////////////////////////

    fun showEditAvatarDialog() {
        _uiState.update {
            it.copy(
                activeDialog = ProfileDialog.EditAvatar
            )
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
                activeDialog = ProfileDialog.EditBirth
            )
        }
    }

    fun saveBirth() {
        _uiState.update {
            it.copy(
                activeDialog = ProfileDialog.None,
                editedBirthDate = null,
            )
        }
    }
    ///

    fun showDatePicker() {
        _uiState.update {
            it.copy(
                activeDialog = ProfileDialog.DatePicker(returnTo = ProfileDialog.EditBirth)
            )
        }
    }

    fun onBirthDateSelected(dateMillis: Long) {
        _uiState.update {
            val dialog = it.activeDialog
            if (dialog is ProfileDialog.DatePicker) {
                it.copy(
                    editedBirthDate = dateMillis,
                    activeDialog = dialog.returnTo
                )
            } else it
        }
    }

    ///////////////////////////dialog name///////////////////////////////////

    fun showEditNameDialog() {
        _uiState.update {
            it.copy(
                activeDialog = ProfileDialog.EditName,
                editedName = it.profile?.name.orEmpty()
            )
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
                activeDialog = ProfileDialog.None
            )
        }
    }

}

data class ProfileUiState(
    val profile: UserProfile? = null,
    val activeDialog: ProfileDialog = ProfileDialog.None,

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