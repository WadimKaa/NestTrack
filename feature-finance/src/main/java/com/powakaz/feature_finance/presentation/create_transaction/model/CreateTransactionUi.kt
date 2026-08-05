package com.powakaz.feature_finance.presentation.create_transaction.model

import androidx.compose.ui.graphics.Color
import com.powakaz.feature_finance.domain.model.Currency
import com.powakaz.feature_finance.domain.model.Wallet
import com.powakaz.feature_finance.domain.model.WalletType
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
        "(сегодня)"
    } else if (selectedDate == todayDate.minusDays(1)) {
        "(вчера)"
    } else if (selectedDate == todayDate.minusDays(2)) {
        "(позавчера)"
    } else {
        ""
    }

    val MAX_NAME_LETTER_COUNT : Int = 20
    val letterCount = name.length

    val quickDateActions = createQuickActions()

    private fun createQuickActions(): List<QuickActionDate> {
        return listOf(
            QuickActionDate(
                LocalDate.now(), "Сегодня", LocalDate.now().format(dateFormatter)
            ),
            QuickActionDate(
                LocalDate.now().minusDays(1),
                "Вчера",
                LocalDate.now().minusDays(1).format(dateFormatter)
            ),
            QuickActionDate(
                LocalDate.now().minusDays(2),
                "Позавчера",
                LocalDate.now().format(dateFormatter)
            )
        )
    }
}

enum class WalletInitType {INIT, NOT_INIT}

data class WalletUi(
    val initType: WalletInitType,
    val id: Int?,
    val userId: Int?,
    val name: String,
    val balanceLabel: String,
    val balance : Float,
    val iconId : Int
){
    companion object{
        fun getInitWallet() : WalletUi{
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


data class QuickActionDate(val localDate: LocalDate, val label: String, val readableDate: String)


data class CategoryUi(
    val id: Int,
    val userId: Int?,
    val name: String,
    val iconResourceId: Int,
    val iconColor: Color
)