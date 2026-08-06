package com.powakaz.feature_finance.presentation.create_transaction.model

import androidx.compose.ui.graphics.Color
import com.powakaz.feature_finance.R
import com.powakaz.feature_finance.utils.UiText
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


data class CreateTransactionUiState(
    val name: String = "",
    val amount: Int = 0,
    val maxAmountLetterCount: Int = 5,
    val selectedCategoryIndex: Int = -1,
    val wallets: List<WalletUi> = listOf(),
    val categories: List<CategoryUi> = listOf(),
    val fromWallet: WalletUi = WalletUi.getInitWallet(),
    val toWallet: WalletUi = WalletUi.getInitWallet(),
    val isWalletDialogVisible: Boolean = false,
    val walletDialogTarget: WalletDialogTarget = WalletDialogTarget.FROM,
    val isDateDialogVisible: Boolean = false,
    val todayDate: LocalDate = LocalDate.now(),
    val selectedDate: LocalDate = LocalDate.now(),
    val tempSelectedDate: LocalDate = LocalDate.now(),
) {

    private val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy", Locale("ru"))

    val clickDateLabel = if (selectedDate == todayDate) {
        UiText.StringResource(R.string.transaction_today_clicker)
    } else if (selectedDate == todayDate.minusDays(1)) {
        UiText.StringResource(R.string.transaction_yesterday_clicker)
    } else if (selectedDate == todayDate.minusDays(2)) {
        UiText.StringResource(R.string.transaction_before_yesterday_clicker)
    } else {
        UiText.StringResource(R.string.transaction_null_clicker)
    }

    val MAX_NAME_LETTER_COUNT: Int = 20
    val MIN_NAME_LETTER_COUNT: Int = 3
    val letterCount = name.length

    val quickDateActions = createQuickActions()

    private fun createQuickActions(): List<QuickActionDate> {
        return listOf(
            QuickActionDate(
                LocalDate.now(),
                UiText.StringResource(R.string.transaction_today),
                LocalDate.now().format(dateFormatter)
            ),
            QuickActionDate(
                LocalDate.now().minusDays(1),
                UiText.StringResource(R.string.transaction_yesterday),
                LocalDate.now().minusDays(1).format(dateFormatter)
            ),
            QuickActionDate(
                LocalDate.now().minusDays(2),
                UiText.StringResource(R.string.transaction_before_yesterday),
                LocalDate.now().format(dateFormatter)
            )
        )
    }

    var error = ScreenState.SHORT_NAME_ERROR
    val isCanSave = getSavePossibility()


    private fun getSavePossibility(): Boolean {
        return when {
            letterCount <= MIN_NAME_LETTER_COUNT -> {
                error = ScreenState.SHORT_NAME_ERROR
                false
            }

            fromWallet.id == toWallet.id -> {
                error = ScreenState.SAME_WALLET_ERROR
                false
            }

            amount == 0 -> {
                error = ScreenState.NULL_TRANSACTION_ERROR
                false
            }

            amount > fromWallet.balance -> {
                error = ScreenState.NOT_ENOUGH_MONEY_ERROR
                false
            }

            else -> {
                true
            }

        }
    }
}

enum class ScreenState { SHORT_NAME_ERROR, SAME_WALLET_ERROR, NOT_ENOUGH_MONEY_ERROR, NULL_TRANSACTION_ERROR, }
enum class WalletInitType { INIT, NOT_INIT }

data class WalletUi(
    val initType: WalletInitType,
    val id: Int?,
    val userId: Int?,
    val name: String,
    val balanceLabel: String,
    val balance: Float,
    val iconId: Int
) {
    companion object {
        fun getInitWallet(): WalletUi {
            return WalletUi(
                initType = WalletInitType.INIT,
                id = null,
                userId = null,
                name = "",
                balanceLabel = "",
                balance = 0f,
                iconId = -1
            )
        }
    }
}


enum class WalletDialogTarget { FROM, TO }


data class QuickActionDate(val localDate: LocalDate, val label: UiText.StringResource, val readableDate: String)


data class CategoryUi(
    val id: Int,
    val userId: Int?,
    val name: String,
    val iconResourceId: Int,
    val iconColor: Color
)