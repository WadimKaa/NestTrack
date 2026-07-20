package com.powakaz.feature_finance.presentation.mapper

import android.content.Context
import androidx.compose.ui.graphics.Color

import androidx.core.graphics.toColorInt
import com.powakaz.feature_finance.domain.model.FinanceDay
import com.powakaz.feature_finance.domain.model.Transaction
import com.powakaz.feature_finance.presentation.model.FinanceDayUiState
import com.powakaz.feature_finance.presentation.model.TransactionUiState
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.text.toInt

class FinanceDayUiStateMapper @Inject constructor(@ApplicationContext private val context: Context) {

    private val DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy")


    fun map(financeDay: FinanceDay): FinanceDayUiState {
        return FinanceDayUiState(
            id = financeDay.transactions[0].id,
            title = financeDay.title,
            transactionDate = financeDay.transactionDate.format(DATE_FORMATTER),
            income = "+${financeDay.income.toInt()} BYN",
            outgo = "${financeDay.outgo.toInt()} BYN",
            transactions = financeDay.transactions.map { mapTransaction(it) }
        )
    }

    private fun mapTransaction(transaction: Transaction): TransactionUiState {
        return TransactionUiState(
            name = transaction.name,
            categoryName = transaction.categoryName,
            iconCircleColor = Color(transaction.iconCircleColor.toColorInt()),
            iconResourceId = context.resources.getIdentifier(
                "ic_${transaction.iconId}_category",
                "drawable",
                context.packageName
            ),
            amount = if (transaction.amount < 0) "${transaction.amount.toInt()} BYN" else "+${transaction.amount.toInt()} BYN",
            type = transaction.type,
            amountColor = if (transaction.amount.toInt() < 0) Color(0XFFf20302) else Color(0XFF0bae31)

        )
    }

}