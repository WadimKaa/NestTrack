package com.powakaz.feature_finance.data.mapper

import com.powakaz.feature_finance.data.remote.model.GetWalletsDto
import com.powakaz.feature_finance.domain.model.Currency
import com.powakaz.feature_finance.domain.model.Wallet
import com.powakaz.feature_finance.domain.model.WalletType

fun GetWalletsDto.toDomain(): Wallet {
    return Wallet(
        id = this.id,
        userId = this.userId,
        name = this.name,
        description = this.description,
        currency = if (this.currency == "BYN") Currency.BYN else Currency.USD,
        type = if (this.type == "cash") WalletType.CASH else WalletType.CARD,
        balance = this.balance,
        iconId = -1
    )
}