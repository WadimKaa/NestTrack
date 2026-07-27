package com.powakaz.nesttrack.feature_time.pres.utils.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import com.powakaz.nesttrack.feature_time.domain.model.Activities
import com.powakaz.nesttrack.feature_time.domain.model.Concession
import com.powakaz.nesttrack.feature_time.pres.model.ActivitiesUi
import com.powakaz.nesttrack.feature_time.pres.model.ConcessionUi
import com.powakaz.nesttrack.feature_time.pres.utils.formatter.DateFormatter
import com.powakaz.nesttrack.feature_time.pres.utils.formatter.DateFormatter.formatDate
import com.powakaz.nesttrack.feature_time.pres.utils.formatter.DateFormatter.formatDurationHours
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
fun Concession.toUi() = ConcessionUi(
    id = id,
    giverName = giverName,
    receiverName = receiverName,
    activityName = activityName,
    activityIcon = activityIcon,
    activityIconColor = activityIconColor.findActivitiesColorToUi(),
    durationHours = formatDurationHours(durationHours),
    description = description,
    createdAt = createdAt,
    activityBackgroundColor = activityIconColor.findActivitiesColorToUi(alpha = 0.2f),
    activityDate = formatDate(activityDate) // форматируем
)

fun Activities.toUi() = ActivitiesUi(
    id = id,
    name = name,
    iconName = iconName,
    iconColor = iconColor.findActivitiesColorToUi(),
    backgroundColor = iconColor.findActivitiesColorToUi(alpha = 0.2f)
)