package com.powakaz.feature_finance.presentation.create_transaction.model

import androidx.compose.ui.graphics.Color

data class CategoryUi(
    val id: Int,
    val userId: Int?,
    val name: String,
    val iconResourceId: Int,
    val iconColor: Color
)