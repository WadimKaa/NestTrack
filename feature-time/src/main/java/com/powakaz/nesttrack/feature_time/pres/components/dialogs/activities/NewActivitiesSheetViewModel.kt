package com.powakaz.nesttrack.feature_time.pres.components.dialogs.activities

import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powakaz.core_common.R
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.nesttrack.feature_time.domain.model.activities.create.CreateActivitiesRequest
import com.powakaz.nesttrack.feature_time.domain.model.activities.create.CreateActivitiesResponse
import com.powakaz.nesttrack.feature_time.domain.usecase.CreateNewActivitiesUseCase
import com.powakaz.nesttrack.feature_time.pres.model.ActivitiesUi
import com.powakaz.nesttrack.feature_time.pres.screen.TimeTrackingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewActivitiesSheetViewModel @Inject constructor(
    val createNewActivitiesUseCase: CreateNewActivitiesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewActivitiesUiState())

    val uiState: StateFlow<NewActivitiesUiState> = _uiState.asStateFlow()

    init {

    }

    fun onSelectedIcon(icon: Int) {
        _uiState.update {
            it.copy(selectedIcon = icon)
        }
    }

    fun onSelectedColor(color: Color) {
        _uiState.update {
            it.copy(selectedColor = color)
        }
    }

    fun onNameChanged(name: String) {
        _uiState.update {
            it.copy(
                activitiesName = name,
                hasEditedName = true
            )
        }
    }

    fun onSaveActivities() {

        val state = _uiState.value

        val request = CreateActivitiesRequest(
            name = state.activitiesName,
            iconName = state.selectedIcon!!,
            iconColor = state.selectedColor!!
        )

        viewModelScope.launch {

            val result = createNewActivitiesUseCase(request)

            when (result) {
                is NetworkResult.Success<CreateActivitiesResponse> -> {
                    if (result.data.status) {
                        _uiState.update {
                            it.copy(
                                activitiesName = "",
                                selectedColor = null,
                                selectedIcon = null,
                                hasEditedName = false
                            )
                        }
                    } else {
                        _uiState.update {
                            it.copy(error = "Не удалось сохранить активность")
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

}

data class NewActivitiesUiState(
    val activitiesName: String = "",
    val selectedIcon: Int? = null,
    val selectedColor: Color? = null,

    val hasEditedName: Boolean = false,
    val error: String? = null,

    ) {
    val isCreateButtonEnabled =
        activitiesName.isNotBlank() &&
                selectedIcon != null &&
                selectedColor != null
}

