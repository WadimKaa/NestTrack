package com.powakaz.feature_finance.presentation.create_transaction.mapper

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import com.powakaz.feature_finance.domain.model.Category
import com.powakaz.feature_finance.presentation.create_transaction.model.CategoryUi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CategoryUiMapper @Inject constructor(@ApplicationContext private val context : Context) {

    fun map(category : Category) : CategoryUi{
        return CategoryUi(
            id = category.id,
            userId = category.userId,
            name = category.name,
            iconResourceId = context.resources.getIdentifier(
                "ic_${category.iconName}_category",
                "drawable",
                context.packageName
            ),
            iconColor = Color(category.colorHex.toColorInt())
        )
    }
}