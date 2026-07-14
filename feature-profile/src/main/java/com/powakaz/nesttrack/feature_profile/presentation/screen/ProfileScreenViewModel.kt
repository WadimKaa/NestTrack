package com.powakaz.nesttrack.feature_profile.presentation.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_profile.domain.model.UpdateProfile
import com.powakaz.nesttrack.feature_profile.domain.model.UserProfile
import com.powakaz.nesttrack.feature_profile.domain.usecase.GetProfileUseCase
import com.powakaz.nesttrack.feature_profile.domain.usecase.UpdateAvatarUseCase
import com.powakaz.nesttrack.feature_profile.domain.usecase.UpdateProfileUseCase
import com.powakaz.nesttrack.feature_profile.presentation.state.ProfileDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
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
                    val profileData = result.data

                    _uiState.update {
                        it.copy(
                            profile = profileData
                        )
                    }
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

    fun onAvatarSelected(photo: Any) {
        val profile = _uiState.value.profile ?: return

        viewModelScope.launch {
            val avatarUrl = updateAvatarUseCase(photo)

            _uiState.update {
                it.copy(
                    selectedAvatar = photo,
                    profile = profile.copy(
                        avatarUrl = avatarUrl
                    )
                )
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
        val profile = _uiState.value.profile ?: throw Exception("Profile not found")
        val birthDate = _uiState.value.editedBirthDate ?: throw Exception("birth Date not found")

        viewModelScope.launch {
            val updateProfile = profile.copy(
                birthDate = birthDate
            )

            updateProfileInfo(updateProfile)
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

    fun onBirthDateSelected(date: LocalDate) {
        _uiState.update {
            val dialog = it.activeDialog
            if (dialog is ProfileDialog.DatePicker) {
                it.copy(
                    editedBirthDate = date,
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
                editedName = ""
            )
        }
    }

    fun onNameChanged(name: String) {
        _uiState.update {
            it.copy(editedName = name)
        }
    }

    fun saveName() {
        val profile = _uiState.value.profile ?: throw Exception("Profile not found") //IllegalStateException

        viewModelScope.launch {
            val updateProfile = profile.copy(
                name = _uiState.value.editedName
            )

            updateProfileInfo(updateProfile)
        }
    }

    suspend fun updateProfileInfo(updateProfile: UserProfile) {
        val result = updateProfileUseCase(updateProfile)
        when(result) {
            is NetworkResult.Success<UpdateProfile> -> {
                if (result.data.status) {
                    _uiState.update {
                        it.copy(
                            profile = updateProfile,
                            editedName = "",
                            activeDialog = ProfileDialog.None
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(error = "Не удалось сохранить профиль")
                    }
                }
            }
            is NetworkResult.Error -> {
                NetworkResult.Error(result.code, result.message)
            }
            is NetworkResult.Exception -> {
                NetworkResult.Exception(result.e)
            }
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
    val editedBirthDate: LocalDate? = null,

    val isEditAvatarDialogVisible: Boolean = false,
    val selectedAvatar: Any? = null,

    val error: String? = null,

    ) {
    val isSaveEnabled: Boolean
        get() = editedName.isNotBlank() &&
                editedName != profile?.name
}

