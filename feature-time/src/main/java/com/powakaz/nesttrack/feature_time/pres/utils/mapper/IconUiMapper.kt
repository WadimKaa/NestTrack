package com.powakaz.nesttrack.feature_time.pres.utils.mapper

import android.content.Context


fun Context.findActivitiesIconToUi(name: String): Int {
    val id = resources.getIdentifier(
        "ic_${name}_activities",
        "drawable",
        packageName
    )

    return id
}