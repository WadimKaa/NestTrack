package com.powakaz.feature_finance.data.mapper

import com.powakaz.feature_finance.data.remote.model.GetCategoriesDto
import com.powakaz.feature_finance.domain.model.Category


fun GetCategoriesDto.toDomain() : Category{
    return Category(
        id = this.id,
        userId = this.userId,
        name = this.name,
        iconName = iconName,
        colorHex = colorHex
    )
}