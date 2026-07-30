package com.powakaz.feature_finance.presentation.create_transaction

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powakaz.core_network.model.NetworkResult
import com.powakaz.feature_finance.domain.constants.FinanceConstants
import com.powakaz.feature_finance.domain.model.Category
import com.powakaz.feature_finance.domain.model.CreateTransactionData
import com.powakaz.feature_finance.domain.model.Currency
import com.powakaz.feature_finance.domain.model.Wallet
import com.powakaz.feature_finance.domain.model.WalletType
import com.powakaz.feature_finance.domain.usecase.GetCreateTransactionDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class WalletDialogTarget { FROM, TO }

data class CreateTransactionUiState(
    val name: String = "",
    val amount: Int = 0,
    val maxAmountLetterCount: Int = 5,
    val selectedCategoryIndex: Int = 0,
    val wallets: List<Wallet> = listOf(),
    val categories: List<Category> = listOf(),
    val fromWallet: Wallet? = null,
    val toWallet: Wallet? = null,
    val isWalletDialogVisible: Boolean = false,
    val walletDialogTarget: WalletDialogTarget = WalletDialogTarget.FROM
)

sealed interface DialogState {
    object None : DialogState

}

sealed interface CreateTransactionEvent {
    data class NameChange(val text: String) : CreateTransactionEvent
    data class OpenWalletPicker(val walletDialogTarget: WalletDialogTarget) : CreateTransactionEvent
    data class SelectWallet(val walletId: Int?) : CreateTransactionEvent
    object CloseWalletPicker : CreateTransactionEvent
    data class AmountChange(val amount: Int) : CreateTransactionEvent
    data class IncreaseAmount(val increaseAmount: Int) : CreateTransactionEvent

}

@HiltViewModel
class CreateTransactionViewModel @Inject constructor(private val getCreateTransactionDataUseCase: GetCreateTransactionDataUseCase) :
    ViewModel() {
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
                            wallets = result.data.wallets,
                            categories = result.data.categories,
                            fromWallet = result.data.wallets.find { it.userId == result.data.userId && it.type == WalletType.CASH }
                                ?: null,
                            toWallet = result.data.wallets.find { it.id == FinanceConstants.WEEKLY_WALLET_ID }
                                ?: null,
                        )
                    }
                }
            }
        }
    }


    fun onEvent(createTransactionEvent: CreateTransactionEvent) {
        when (createTransactionEvent) {
            is CreateTransactionEvent.NameChange -> {
                _uiState.update {
                    it.copy(name = createTransactionEvent.text)
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
                            fromWallet = _uiState.value.wallets.find { it.id == createTransactionEvent.walletId }
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            toWallet = _uiState.value.wallets.find { it.id == createTransactionEvent.walletId }
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
        }
    }


}