package com.powakaz.nesttrack.feature_profile.presentation.components.dialogs.birth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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


@Composable
fun BirthdayDatePicker(
    selectedDateMillis: Long?,
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit
) {

    Dialog(
        onDismissRequest = onDismiss,
    ) {
        BirthdayDatePickerContent(
            selectedDateMillis = selectedDateMillis,
            onDismiss = onDismiss,
            onDateSelected = onDateSelected
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayDatePickerContent(
    selectedDateMillis: Long?,
    onDismiss: () -> Unit,
    onDateSelected: (Long) -> Unit,
) {

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = selectedDateMillis)

    /*DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            SaveButton(
                text = "OK",
                onClick = {
                    datePickerState.selectedDateMillis?.let (onDateSelected)
                },
                onEnabled = true,
                modifier = Modifier.size(80.dp)
            )
        },
        dismissButton = {
            CancelButton(
                text = "Отмена",
                onClick = onDismiss,
                modifier = Modifier.size(80.dp)
            )
        }
    ) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                selectedDayContainerColor = Color(0xFFF1749E), // кружок выбранной даты
                selectedDayContentColor = Color.White,         // цвет цифры

                selectedYearContainerColor = Color(0xFFA17CDE),
                selectedYearContentColor = Color.White
            )
        )
    }*/



    Card {
        Column(modifier = Modifier.background(Color.White)) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    selectedDayContainerColor = Color(0xFFF1749E), // кружок выбранной даты
                    selectedDayContentColor = Color.White,         // цвет цифры

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
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                SaveButton(
                    text = "OK",
                    onClick = {
                        datePickerState.selectedDateMillis?.let (onDateSelected)
                    },
                    onEnabled = true,
                    modifier = Modifier.size(80.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}


@Preview(showBackground = true)
@Composable
fun BirthdayDatePickerPreview() {
    BirthdayDatePickerContent(onDismiss = {}, onDateSelected = {}, selectedDateMillis = 1123456L)
}