package com.powakaz.nesttrack.feature_profile.presentation.components.dialogs.birth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.powakaz.nesttrack.feature_profile.presentation.components.buttons.CancelButton
import com.powakaz.nesttrack.feature_profile.presentation.components.buttons.SaveButton
import java.time.LocalDate
import java.time.ZoneId
import kotlin.time.Instant


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BirthdayDatePicker(
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {

    Dialog(
        onDismissRequest = onDismiss,
    ) {
        BirthdayDatePickerContent(
            selectedDate = selectedDate,
            onDismiss = onDismiss,
            onDateSelected = onDateSelected
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayDatePickerContent(
    selectedDate: LocalDate?,
    onDismiss: () -> Unit,
    onDateSelected: (LocalDate) -> Unit,
) {

    val initialDateMillis = selectedDate?.atStartOfDay(ZoneId.systemDefault())
        ?.toInstant()?.toEpochMilli()
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialDateMillis)

    Card {
        Column(modifier = Modifier.background(Color.White)) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    selectedDayContainerColor = Color(0xFFF1749E),
                    selectedDayContentColor = Color.White,

                    selectedYearContainerColor = Color(0xFFA17CDE),
                    selectedYearContentColor = Color.White,

                    containerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                CancelButton(
                    text = "Отмена",
                    onClick = onDismiss,
                    modifier = Modifier.width(140.dp).height(50.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                SaveButton(
                    text = "OK",
                    onClick = {
                        datePickerState.selectedDateMillis?.let {
                            onDateSelected(
                                java.time.Instant.ofEpochMilli(it)
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()
                            )
                        }
                    },
                    onEnabled = true,
                    modifier = Modifier.size(80.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun BirthdayDatePickerPreview() {
    BirthdayDatePickerContent(onDismiss = {}, onDateSelected = {}, selectedDate = null)
}