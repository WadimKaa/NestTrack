package com.powakaz.nesttrack.feature_profile.presentation.screen

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_profile.data.datasourse.remote.model.ProfileDto
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import com.powakaz.nesttrack.feature_profile.domain.usecase.GetProfileUseCase
import com.powakaz.nesttrack.feature_profile.domain.usecase.UpdateAvatarUseCase
import com.powakaz.nesttrack.feature_profile.domain.usecase.UpdateBirthDateUseCase
import com.powakaz.nesttrack.feature_profile.domain.usecase.UpdateNameUseCase
import com.powakaz.nesttrack.feature_profile.presentation.state.ProfileDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.powakaz.nesttrack.feature_profile.presentation.utils.formatDate
import com.powakaz.nesttrack.feature_profile.presentation.utils.formatDateMountToText

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val updateNameUseCase: UpdateNameUseCase,
    private val updateBirthDateUseCase: UpdateBirthDateUseCase,
    private val updateAvatarUseCase: UpdateAvatarUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = getProfileUseCase()
            when (result) {
                is NetworkResult.Error -> {
                    NetworkResult.Error(result.code, result.message)
                }

                is NetworkResult.Exception -> {
                    NetworkResult.Exception(result.e)
                }

                is NetworkResult.Success<UserProfile> -> {
                    Log.e("LOl", result.toString())

                    val profileData = result.data

                    _uiState.update {
                        it.copy(
                            profile = profileData,
                            formattedCreatedAt = profileData.createdAt?.let { date ->
                                formatDateMountToText(date)
                            } ?: "Дата не указана"

                        )
                    }

                    NetworkResult.Success(Unit)
                }
            }


        }
    }


    ///////////////////////////dismiss all dialog///////////////////////////////////////

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
        viewModelScope.launch {
            updateAvatarUseCase(uri)
            _uiState.update {
                it.copy(selectedAvatar = uri)
            }
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
        val dateToSave = _uiState.value.editedBirthDate
        if (dateToSave != null) {
            viewModelScope.launch {
                updateBirthDateUseCase(dateToSave)
                _uiState.update {
                    it.copy(
                        activeDialog = ProfileDialog.None,
                        editedBirthDate = null
                    )
                }
            }
        }
    }

    /// ///////////////////////////date picker///////////////////////////////////

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
        viewModelScope.launch {
            updateNameUseCase(_uiState.value.editedName)
            _uiState.update {
                it.copy(
                    editedName = "",
                    activeDialog = ProfileDialog.None
                )
            }
        }
    }

}

data class ProfileUiState(
    val profile: UserProfile? = null,
    val formattedCreatedAt: String = "",
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

