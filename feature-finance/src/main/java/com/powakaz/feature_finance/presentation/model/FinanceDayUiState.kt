package com.powakaz.feature_finance.presentation.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.powakaz.feature_finance.domain.model.WalletType
import java.time.LocalDate

@Immutable
data class FinanceDayUiState(
    val id : Int,
    val title: String,
    val transactionDate : String,
    val income: String,
    val outgo: String,
    val transactions: List<TransactionUiState>
)

@Immutable
data class TransactionUiState(
    val name: String,
    val categoryName: String,
    val iconCircleColor: Color,
    val iconResourceId : Int,
    val amount: String,
    val type: WalletType,
    val amountColor : Color
)