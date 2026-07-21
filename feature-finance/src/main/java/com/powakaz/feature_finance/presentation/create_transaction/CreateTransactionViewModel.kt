package com.powakaz.feature_finance.presentation.create_transaction

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject



data class CreateTransactionUiState(
    val name : String = ""
)

@HiltViewModel
class CreateTransactionViewModel @Inject constructor() : ViewModel(){
    private val _uiState = MutableStateFlow(CreateTransactionUiState())
    val uiState = _uiState.asStateFlow()




}