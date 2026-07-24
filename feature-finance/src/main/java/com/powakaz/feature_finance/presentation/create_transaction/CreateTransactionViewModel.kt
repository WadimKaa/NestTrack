package com.powakaz.feature_finance.presentation.create_transaction

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject





data class CreateTransactionUiState(
    val name: String = "",
    val selectedCategoryIndex: Int = 0
)

sealed interface DialogState{
    object None : DialogState

}

sealed interface CreateTransactionEvent {
    data class NameChange(val text: String) : CreateTransactionEvent

}

@HiltViewModel
class CreateTransactionViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(CreateTransactionUiState())
    val uiState = _uiState.asStateFlow()




    fun onEvent(createTransactionEvent: CreateTransactionEvent){
        when(createTransactionEvent){
            is CreateTransactionEvent.NameChange -> {
                _uiState.update {
                    it.copy(name = createTransactionEvent.text)
                }
            }
        }
    }


}