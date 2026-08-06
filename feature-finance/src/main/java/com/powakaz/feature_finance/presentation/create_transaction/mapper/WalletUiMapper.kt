package com.powakaz.feature_finance.presentation.create_transaction.mapper

import android.content.Context
import com.powakaz.feature_finance.R
import com.powakaz.feature_finance.domain.model.Wallet
import com.powakaz.feature_finance.domain.model.WalletType
import com.powakaz.feature_finance.presentation.create_transaction.model.WalletInitType
import com.powakaz.feature_finance.presentation.create_transaction.model.WalletUi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WalletUiMapper @Inject constructor(@ApplicationContext private val context: Context) {

    fun map(wallet : Wallet) : WalletUi{
        return WalletUi(
            initType = WalletInitType.NOT_INIT,
            id = wallet.id,
            userId = wallet.userId,
            name = wallet.name,
            balanceLabel = if (wallet.id == null) context.getString(R.string.transaction_inifinity) else wallet.balance.toInt().toString(),
            balance = wallet.balance,
            iconId = when (wallet.type) {
                WalletType.CARD -> R.drawable.ic_card
                WalletType.CASH -> R.drawable.ic_cash
                else -> R.drawable.ic_cash_register
            }
        )
    }
}