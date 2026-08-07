package com.powakaz.feature_finance.domain.model

import kotlinx.serialization.SerialName
import java.time.LocalDate

data class CreateTransaction (
    val fromWalletId : Int?,
    val toWalletId : Int?,
    val categoryId : Int,
    val name : String,
    val description : String,
    val amount : Float,
    val transactionDate : LocalDate
)