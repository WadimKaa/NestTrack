package com.powakaz.feature_finance.presentation.create_transaction

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.feature_finance.domain.constants.FinanceConstants
import com.powakaz.feature_finance.domain.model.CreateTransactionData
import com.powakaz.feature_finance.domain.model.Wallet
import com.powakaz.feature_finance.domain.model.WalletType
import com.powakaz.feature_finance.domain.usecase.CreateTransactionUseCase
import com.powakaz.feature_finance.domain.usecase.GetCreateTransactionDataUseCase
import com.powakaz.feature_finance.presentation.create_transaction.mapper.CategoryUiMapper
import com.powakaz.feature_finance.presentation.create_transaction.mapper.WalletUiMapper
import com.powakaz.feature_finance.presentation.create_transaction.mapper.toDomain
import com.powakaz.feature_finance.presentation.create_transaction.model.CreateTransactionUiState
import com.powakaz.feature_finance.presentation.create_transaction.model.ScreenState
import com.powakaz.feature_finance.presentation.create_transaction.model.WalletDialogTarget
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

sealed interface CreateTransactionEvent {
    data class NameChange(val text: String) : CreateTransactionEvent
    data class OpenWalletPicker(val walletDialogTarget: WalletDialogTarget) : CreateTransactionEvent
    object CloseWalletPicker : CreateTransactionEvent
    data class SelectWallet(val walletId: Int?) : CreateTransactionEvent
    data class AmountChange(val amount: Int) : CreateTransactionEvent
    data class IncreaseAmount(val increaseAmount: Int) : CreateTransactionEvent
    data class SelectCategory(val categoryIndex: Int) : CreateTransactionEvent
    object OpenDateDialog : CreateTransactionEvent
    object CloseDateDialog : CreateTransactionEvent
    object SaveDate : CreateTransactionEvent
    data class SelectTempDate(val tempLocalDate: LocalDate) : CreateTransactionEvent
    object ClickSaveButton : CreateTransactionEvent
}

sealed interface UiEvent {
    object ShowErrorToast : UiEvent
}

@HiltViewModel
class CreateTransactionViewModel @Inject constructor(
    private val getCreateTransactionDataUseCase: GetCreateTransactionDataUseCase,
    private val categoryUiMapper: CategoryUiMapper,
    private val walletUiMapper: WalletUiMapper,
    private val createTransactionUseCase: CreateTransactionUseCase
) :
    ViewModel() {

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()
        
    private val _uiState = MutableStateFlow(CreateTransactionUiState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            val result = getCreateTransactionDataUseCase()

            when (result) {
                is NetworkResult.Error -> TODO()
                is NetworkResult.Exception -> {
                    Log.e("LOL", result.e.toString())
                }

                is NetworkResult.Success<CreateTransactionData> -> {
                    _uiState.update {
                        it.copy(
                            wallets = result.data.wallets.map { walletUiMapper.map(it) },
                            categories = result.data.categories.map { categoryUiMapper.map(it) },
                            fromWallet = walletUiMapper.map(result.data.wallets.find { it.userId == result.data.userId && it.type == WalletType.CASH }
                                ?: Wallet.getExternalWallet()),
                            toWallet = walletUiMapper.map(result.data.wallets.find { it.id == FinanceConstants.WEEKLY_WALLET_ID }
                                ?: Wallet.getExternalWallet()),
                            selectedCategoryIndex = result.data.categories.first().id
                        )
                    }
                }
            }
        }
    }


    fun onEvent(createTransactionEvent: CreateTransactionEvent) {
        when (createTransactionEvent) {
            is CreateTransactionEvent.NameChange -> {
                if (createTransactionEvent.text.length <= _uiState.value.MAX_NAME_LETTER_COUNT) {
                    _uiState.update {
                        it.copy(name = createTransactionEvent.text)
                    }
                }
            }

            is CreateTransactionEvent.OpenWalletPicker -> {
                _uiState.update {
                    it.copy(
                        isWalletDialogVisible = true,
                        walletDialogTarget = createTransactionEvent.walletDialogTarget
                    )
                }
            }

            CreateTransactionEvent.CloseWalletPicker -> {
                _uiState.update {
                    it.copy(
                        isWalletDialogVisible = false
                    )
                }
            }

            is CreateTransactionEvent.SelectWallet -> {
                if (_uiState.value.walletDialogTarget == WalletDialogTarget.FROM) {
                    _uiState.update {
                        it.copy(
                            fromWallet = _uiState.value.wallets.find { it.id == createTransactionEvent.walletId }!!
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            toWallet = _uiState.value.wallets.find { it.id == createTransactionEvent.walletId }!!
                        )
                    }
                }

                viewModelScope.launch {
                    delay(160)
                    _uiState.update {
                        it.copy(
                            isWalletDialogVisible = false
                        )
                    }
                }
            }

            is CreateTransactionEvent.AmountChange -> {
                if (createTransactionEvent.amount.toString().length <= _uiState.value.maxAmountLetterCount) {
                    _uiState.update {
                        it.copy(
                            amount = createTransactionEvent.amount
                        )
                    }
                }
            }

            is CreateTransactionEvent.IncreaseAmount -> {
                if ((_uiState.value.amount + createTransactionEvent.increaseAmount).toString().length <= _uiState.value.maxAmountLetterCount) {
                    _uiState.update {
                        it.copy(
                            amount = _uiState.value.amount + createTransactionEvent.increaseAmount
                        )
                    }
                }
            }

            is CreateTransactionEvent.SelectCategory -> {
                _uiState.update {
                    it.copy(selectedCategoryIndex = createTransactionEvent.categoryIndex)
                }
            }

            CreateTransactionEvent.CloseDateDialog -> {
                _uiState.update {
                    it.copy(
                        isDateDialogVisible = false,
                        tempSelectedDate = _uiState.value.selectedDate
                    )
                }
            }

            CreateTransactionEvent.OpenDateDialog -> {
                _uiState.update {
                    it.copy(
                        isDateDialogVisible = true
                    )
                }
            }

            is CreateTransactionEvent.SaveDate -> {
                _uiState.update {
                    it.copy(
                        selectedDate = _uiState.value.tempSelectedDate
                    )
                }
            }

            is CreateTransactionEvent.SelectTempDate -> {
                _uiState.update { it.copy(tempSelectedDate = createTransactionEvent.tempLocalDate) }
            }

            CreateTransactionEvent.ClickSaveButton -> {
                if (_uiState.value.isCanSave){
                    viewModelScope.launch {
                        createTransactionUseCase(_uiState.value.toDomain())
                    }
                }else{
                    viewModelScope.launch {
                        _events.emit(UiEvent.ShowErrorToast)
                    }
                }
            }
        }
    }


}