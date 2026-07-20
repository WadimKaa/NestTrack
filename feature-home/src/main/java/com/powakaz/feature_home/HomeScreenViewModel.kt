package com.powakaz.feature_home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class HomeScreenUiState(
    val selectedItem: Int = 1 ///!!!0
) {

}


sealed interface HomeScreenUiEvent {
    data class SelectItem(val itemId: Int) : HomeScreenUiEvent
}

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()


    fun onEvent(homeScreenUiEvent: HomeScreenUiEvent){
        when(homeScreenUiEvent){
            is HomeScreenUiEvent.SelectItem -> {
                _uiState.update {
                    it.copy(selectedItem = homeScreenUiEvent.itemId)
                }
            }
        }
    }
}