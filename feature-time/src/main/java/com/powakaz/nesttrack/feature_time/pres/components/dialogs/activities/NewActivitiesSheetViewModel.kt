package com.powakaz.nesttrack.feature_time.pres.components.dialogs.activities

import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powakaz.core_common.R
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
        val state = uiState.value

        viewModelScope.launch {
            ///
        }
    }

}

data class NewActivitiesUiState(
    val activitiesName: String = "",
    val selectedIcon: Int? = null,
    val selectedColor: Color? = null,

    val hasEditedName: Boolean = false,

    ) {
    val isCreateButtonEnabled =
        activitiesName.isNotBlank() &&
                selectedIcon != null &&
                selectedColor != null
}

